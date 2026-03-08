package pg86491

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name  string
		sizes [][]int
		want  int
	}{
		{
			name:  "기본 케이스",
			sizes: [][]int{{60, 50}, {30, 70}, {60, 30}, {80, 40}},
			want:  4000,
		},
		{
			name:  "엣지 케이스",
			sizes: [][]int{{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}},
			want:  120,
		},
		{
			name:  "입출력 예시 #3",
			sizes: [][]int{{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}},
			want:  133,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.sizes)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {
	// 제한사항: sizes 길이 최대 10,000 / w, h 최대 1,000
	sizes := make([][]int, 10_000)
	for i := range sizes {
		sizes[i] = []int{1000, 1000}
	}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		Solution(sizes)
	}
}
