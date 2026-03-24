package pg86971

import (
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name  string
		n     int
		wires [][]int
		want  int
	}{
		{
			name:  "입출력 예 1",
			n:     9,
			wires: [][]int{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}},
			want:  3,
		},
		{
			name:  "입출력 예 2",
			n:     4,
			wires: [][]int{{1, 2}, {2, 3}, {3, 4}},
			want:  0,
		},
		{
			name:  "입출력 예 3",
			n:     7,
			wires: [][]int{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}},
			want:  1,
		},
		{
			name:  "최소 입력 (n=2)",
			n:     2,
			wires: [][]int{{1, 2}},
			want:  0,
		},
		{
			name:  "스타 그래프 형태",
			n:     5,
			wires: [][]int{{1, 2}, {1, 3}, {1, 4}, {1, 5}},
			want:  3, // 1개(중심) vs 4개 차이
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := Solution(tt.n, tt.wires); got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: n=100인 최악의 케이스(일직선 트리)로 성능 측정
func BenchmarkSolution(b *testing.B) {
	n := 100
	wires := make([][]int, n-1)
	for i := 0; i < n-1; i++ {
		wires[i] = []int{i + 1, i + 2}
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		Solution(n, wires)
	}
}
