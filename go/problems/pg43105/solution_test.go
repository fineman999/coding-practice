package pg43105

import (
	"math/rand"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name     string
		triangle [][]int
		want     int
	}{
		{
			name: "기본 케이스 - 프로그래머스 예제",
			triangle: [][]int{
				{7},
				{3, 8},
				{8, 1, 0},
				{2, 7, 4, 4},
				{4, 5, 2, 6, 5},
			},
			want: 30,
		},
		{
			name:     "엣지 케이스 - 높이가 1인 경우",
			triangle: [][]int{{100}},
			want:     100,
		},
		{
			name: "큰 입력 - 높이 500 (모두 1)",
			triangle: func() [][]int {
				h := 500
				tr := make([][]int, h)
				for i := 0; i < h; i++ {
					tr[i] = make([]int, i+1)
					for j := 0; j <= i; j++ {
						tr[i][j] = 1
					}
				}
				return tr
			}(),
			want: 500,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.triangle)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

func BenchmarkSolution(b *testing.B) {
	// 벤치마크를 위한 데이터 셋업 (높이 500 최악의 케이스)
	h := 500
	triangle := make([][]int, h)
	for i := 0; i < h; i++ {
		triangle[i] = make([]int, i+1)
		for j := 0; j <= i; j++ {
			triangle[i][j] = rand.Intn(10000)
		}
	}

	b.ResetTimer() // 셋업 시간 제외
	for i := 0; i < b.N; i++ {
		// 매번 같은 데이터를 사용하기 위해 필요 시 복사본을 전달하거나
		// 원본 수정을 감안하여 셋업 로직을 조정할 수 있습니다.
		Solution(triangle)
	}
}
