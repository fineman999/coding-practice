package pg43162

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name      string
		n         int
		computers [][]int
		want      int
	}{
		{
			name: "기본 케이스 1",
			n:    3,
			computers: [][]int{
				{1, 1, 0},
				{1, 1, 0},
				{0, 0, 1},
			},
			want: 2,
		},
		{
			name: "기본 케이스 2",
			n:    3,
			computers: [][]int{
				{1, 1, 0},
				{1, 1, 1},
				{0, 1, 1},
			},
			want: 1,
		},
		{
			name: "엣지 케이스 - 모두 분리",
			n:    3,
			computers: [][]int{
				{1, 0, 0},
				{0, 1, 0},
				{0, 0, 1},
			},
			want: 3,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.n, tt.computers)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스 (200개 노드가 일렬로 연결)
func BenchmarkSolution(b *testing.B) {
	n := 200
	computers := make([][]int, n)
	for i := range computers {
		computers[i] = make([]int, n)
		computers[i][i] = 1
		if i > 0 {
			computers[i][i-1] = 1
			computers[i-1][i] = 1
		}
	}

	b.ResetTimer() // 데이터 생성 시간 제외
	for i := 0; i < b.N; i++ {
		Solution(n, computers)
	}
}
