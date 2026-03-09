package pg42862

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		n       int
		lost    []int
		reserve []int
		want    int
	}{
		{
			name:    "기본 케이스",
			n:       5,
			lost:    []int{2, 4},
			reserve: []int{1, 3, 5},
			want:    5,
		},
		{
			name:    "기본 케이스 #2",
			n:       5,
			lost:    []int{2, 4},
			reserve: []int{3},
			want:    4,
		},
		{
			name:    "기본 케이스 #3",
			n:       3,
			lost:    []int{3},
			reserve: []int{1},
			want:    2,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.n, tt.lost, tt.reserve)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {
	n := 30
	lost := make([]int, 15)
	reserve := make([]int, 15)
	for i := range n {
		if i%2 == 0 {
			lost[i/2] = i + 1
		} else {
			reserve[i/2] = i + 1
		}
	}
	for i := 0; i < b.N; i++ {
		Solution(n, lost, reserve)
	}
}
