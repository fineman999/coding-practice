package pg1844

import (
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name string
		maps [][]int
		want int
	}{
		{
			name: "기본 케이스 1: 도달 가능",
			maps: [][]int{
				{1, 0, 1, 1, 1},
				{1, 0, 1, 0, 1},
				{1, 0, 1, 1, 1},
				{1, 1, 1, 0, 1},
				{0, 0, 0, 0, 1},
			},
			want: 11,
		},
		{
			name: "기본 케이스 2: 도달 불가능",
			maps: [][]int{
				{1, 0, 1, 1, 1},
				{1, 0, 1, 0, 1},
				{1, 0, 1, 1, 1},
				{1, 1, 1, 0, 0},
				{0, 0, 0, 0, 1},
			},
			want: -1,
		},
		{
			name: "엣지 케이스: 최소 크기(2x2)",
			maps: [][]int{
				{1, 1},
				{1, 1},
			},
			want: 3,
		},
		{
			name: "엣지 케이스: 시작점이 막힌 경우",
			maps: [][]int{
				{1, 0},
				{0, 1},
			},
			want: -1,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// maps가 변조될 수 있으므로 필요시 복사해서 사용하거나 원본 유지 확인
			got := Solution(tt.maps)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: 100x100 최악의 케이스 (S자 경로)
func BenchmarkSolution(b *testing.B) {
	n, m := 100, 100
	worstMap := make([][]int, n)
	for i := range worstMap {
		worstMap[i] = make([]int, m)
		if i%2 == 0 {
			for j := 0; j < m; j++ {
				worstMap[i][j] = 1
			}
		} else {
			openCol := 0
			if (i/2)%2 == 0 {
				openCol = m - 1
			}
			worstMap[i][openCol] = 1
		}
	}
	worstMap[n-1][m-1] = 1

	b.ResetTimer() // 맵 생성 시간 제외
	for i := 0; i < b.N; i++ {
		// Solution이 입력 배열을 수정한다면 루프 내에서 복사본을 전달해야 정확함
		// 여기서는 성능 측정을 위해 원본 전달 (필요 시 복사 로직 추가)
		Solution(worstMap)
	}
}
