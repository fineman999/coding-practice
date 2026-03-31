package pg42885

import (
	"math/rand"
	"testing"
	"time"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		people []int
		limit  int
		want   int
	}{
		{
			name:   "기본 케이스 1",
			people: []int{70, 50, 80, 50},
			limit:  100,
			want:   3,
		},
		{
			name:   "기본 케이스 2",
			people: []int{70, 80, 50},
			limit:  100,
			want:   3,
		},
		{
			name:   "엣지 케이스: 혼자 있는 경우",
			people: []int{40},
			limit:  100,
			want:   1,
		},
		{
			name:   "엣지 케이스: 모두가 무거워 같이 못 타는 경우",
			people: []int{60, 60, 60},
			limit:  100,
			want:   3,
		},
		{
			name:   "엣지 케이스: 2명씩 딱 맞는 경우",
			people: []int{50, 50, 50, 50},
			limit:  100,
			want:   2,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// 원본 데이터 보존을 위해 슬라이스 복사(정렬 시 원본 변경 방지)
			peopleCopy := make([]int, len(tt.people))
			copy(peopleCopy, tt.people)

			got := Solution(peopleCopy, tt.limit)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: 가장 연산량이 많은 최악의 케이스 (50,000명)
func BenchmarkSolution(b *testing.B) {
	rand.Seed(time.Now().UnixNano())
	limit := 240
	// 50,000명의 랜덤한 몸무게 생성
	largePeople := make([]int, 50000)
	for i := 0; i < 50000; i++ {
		largePeople[i] = rand.Intn(201) + 40 // 40 ~ 240 사이
	}

	b.ResetTimer() // 데이터 생성 시간은 제외
	for i := 0; i < b.N; i++ {
		// 테스트 시마다 정렬 상태가 달라질 수 있으므로 복사본 사용
		people := make([]int, len(largePeople))
		copy(people, largePeople)
		Solution(people, limit)
	}
}
