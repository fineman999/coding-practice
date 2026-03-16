package pg42587

import (
	"math/rand"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name       string
		priorities []int
		location   int
		want       int
	}{
		{
			name:       "예제1",
			priorities: []int{2, 1, 3, 2},
			location:   2,
			want:       1,
		},
		{
			name:       "예제2",
			priorities: []int{1, 1, 9, 1, 1, 1},
			location:   0,
			want:       5,
		},
		{
			name:       "모두 같은 우선순위",
			priorities: []int{1, 1, 1, 1},
			location:   2,
			want:       3,
		},
		{
			name:       "내 위치가 가장 앞이고 가장 큰 경우",
			priorities: []int{9, 1, 1, 1},
			location:   0,
			want:       1,
		},
		{
			name:       "내 위치가 가장 뒤이고 한 번에 실행됨",
			priorities: []int{1, 1, 1, 9},
			location:   3,
			want:       1,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.priorities, tt.location)
			if got != tt.want {
				t.Errorf("Solution(%v, %d) = %d, want %d", tt.priorities, tt.location, got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {
	n := 100
	priorities := make([]int, n)
	for i := range priorities {
		priorities[i] = rand.Intn(9) + 1 // 1~9 랜덤 우선순위
	}
	location := rand.Intn(n)

	// 타이머 리셋
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		Solution(priorities, location)
	}
}
