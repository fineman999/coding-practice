package pg42747

import (
	"math/rand"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name      string
		citations []int
		want      int
	}{
		{
			name:      "기본 케이스 - 예제 1",
			citations: []int{3, 0, 6, 1, 5},
			want:      3,
		},
		{
			name:      "기본 케이스 - 예제 2",
			citations: []int{10, 8, 5, 4, 3},
			want:      4,
		},
		{
			name:      "엣지 케이스 - 모두 0인 경우",
			citations: []int{0, 0, 0, 0},
			want:      0,
		},
		{
			name:      "엣지 케이스 - 논문이 1편이고 인용이 많은 경우",
			citations: []int{100},
			want:      1,
		},
		{
			name:      "엣지 케이스 - 모든 논문의 인용수가 논문수보다 큰 경우",
			citations: []int{10, 10, 10},
			want:      3,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.citations)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(1,000편, 랜덤 인용수)
func BenchmarkSolution(b *testing.B) {
	// 벤치마크용 데이터 생성 (최대 1,000편)
	citations := make([]int, 1000)
	for i := 0; i < 1000; i++ {
		citations[i] = rand.Intn(10001)
	}

	b.ResetTimer() // 데이터 생성 시간은 제외
	for i := 0; i < b.N; i++ {
		// 원본 슬라이스가 정렬로 인해 변형될 수 있으므로 매번 복사해서 사용하거나
		// 로직 내에서 복사본을 쓰는지 확인이 필요합니다.
		temp := make([]int, len(citations))
		copy(temp, citations)
		Solution(temp)
	}
}
