package pg42861

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name  string
		n     int
		costs [][]int
		want  int
	}{
		{
			name:  "기본 케이스 (입출력 예시)",
			n:     4,
			costs: [][]int{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}},
			want:  4,
		},
		{
			name:  "엣지 케이스 (섬이 1개)",
			n:     1,
			costs: [][]int{},
			want:  0,
		},
		{
			name:  "엣지 케이스 (섬이 2개)",
			n:     2,
			costs: [][]int{{0, 1, 5}},
			want:  5,
		},
		{
			name:  "모든 비용이 동일한 경우",
			n:     3,
			costs: [][]int{{0, 1, 10}, {1, 2, 10}, {0, 2, 10}},
			want:  20,
		},
		{
			name:  "일직선 연결이 최적일 때",
			n:     4,
			costs: [][]int{{0, 1, 1}, {1, 2, 1}, {2, 3, 1}, {0, 3, 10}},
			want:  3,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.n, tt.costs)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: 섬 100개, 가능한 모든 간선이 있는 최악의 케이스 (약 4,950개 간선)
func BenchmarkSolution(b *testing.B) {
	n := 100
	var costs [][]int
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			costs = append(costs, []int{i, j, (i+j)%100 + 1})
		}
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		Solution(n, costs)
	}
}
