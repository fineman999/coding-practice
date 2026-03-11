package pg42626

import "testing"

func TestSolution(t *testing.T) {
	// 테이블 기반 테스트 정의
	tests := []struct {
		name     string
		scoville []int
		k        int
		expected int
	}{
		{"기본 케이스", []int{1, 2, 3, 9, 10, 12}, 7, 2},
		{"모든 음식을 섞어도 K를 넘길 수 없는 경우 -1 반환", []int{1, 2, 3}, 100, -1},
		{"처음부터 모든 음식의 스코빌 지수가 K 이상인 경우 0 반환", []int{10, 20, 30}, 5, 0},
		{"원소가 2개뿐인 경우에도 정상 동작", []int{1, 2}, 5, 1},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// 원본 슬라이스가 힙 조작으로 훼손되지 않도록 깊은 복사 수행
			scovilleCopy := make([]int, len(tt.scoville))
			copy(scovilleCopy, tt.scoville)

			result := Solution(scovilleCopy, tt.k)
			if result != tt.expected {
				t.Errorf("expected %d, got %d", tt.expected, result)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"

// 가장 연산량이 많은 최악의 케이스(큰 입력)
func BenchmarkSolution(b *testing.B) {
	// 10만 개의 데이터 세팅 (최악의 경우를 상정)
	size := 100000
	k := 1000000000 // K를 아주 크게 잡아 끝까지 섞도록 유도
	originalScoville := make([]int, size)
	for i := 0; i < size; i++ {
		originalScoville[i] = 1
	}

	b.ResetTimer() // 세팅에 걸린 시간은 벤치마크 측정에서 제외

	for i := 0; i < b.N; i++ {
		// Solution 함수 내부에서 원본 배열(슬라이스)을 수정(Pop, Push)하므로
		// 매 반복마다 새로운 배열을 복사해서 넘겨주어야 합니다.
		b.StopTimer() // 복사하는 시간은 측정에서 제외
		scovilleCopy := make([]int, size)
		copy(scovilleCopy, originalScoville)
		b.StartTimer()

		Solution(scovilleCopy, k)
	}
}
