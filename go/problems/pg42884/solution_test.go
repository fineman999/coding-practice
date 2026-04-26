package pg42884

import (
	"math/rand"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		routes [][]int
		want   int
	}{
		{
			name:   "기본 케이스",
			routes: [][]int{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}},
			want:   2,
		},
		{
			name:   "엣지 케이스 - 접점",
			routes: [][]int{{-10, 0}, {0, 10}},
			want:   1,
		},
		{
			name:   "엣지 케이스 - 차량 1대",
			routes: [][]int{{-30000, 30000}},
			want:   1,
		},
		{
			name:   "모두 떨어진 경우",
			routes: [][]int{{1, 2}, {3, 4}, {5, 6}},
			want:   3,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.routes)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: 가장 연산량이 많은 최악의 케이스(10,000대 정렬 및 순회)
func BenchmarkSolution(b *testing.B) {
	// 데이터 준비
	routes := make([][]int, 10000)
	for i := 0; i < 10000; i++ {
		start := rand.Intn(60000) - 30000
		routes[i] = []int{start, start + rand.Intn(1000)}
	}

	b.ResetTimer() // 데이터 생성 시간은 제외
	for i := 0; i < b.N; i++ {
		Solution(routes)
	}
}
