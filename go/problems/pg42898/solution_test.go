package pg42898

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		m       int
		n       int
		puddles [][]int
		want    int
	}{
		{
			name:    "기본 케이스 (입출력 예시)",
			m:       4,
			n:       3,
			puddles: [][]int{{2, 2}},
			want:    4,
		},
		{
			name:    "엣지 케이스: 장애물이 경로를 막음",
			m:       3,
			n:       3,
			puddles: [][]int{{1, 2}, {2, 1}},
			want:    0,
		},
		{
			name:    "엣지 케이스: 일직선 경로에서 중간에 웅덩이",
			m:       1,
			n:       4,
			puddles: [][]int{{1, 2}},
			want:    0,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.m, tt.n, tt.puddles)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스 (100x100, 웅덩이 10개)
func BenchmarkSolution(b *testing.B) {
	m, n := 100, 100
	puddles := [][]int{{2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}, {10, 10}, {11, 11}}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		Solution(m, n, puddles)
	}
}
