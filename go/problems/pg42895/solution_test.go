package pg42895

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		n      int
		number int
		want   int
	}{
		{
			name:   "기본 케이스 1 (N=5, number=12)",
			n:      5,
			number: 12,
			want:   4,
		},
		{
			name:   "기본 케이스 2 (N=2, number=11)",
			n:      2,
			number: 11,
			want:   3,
		},
		{
			name:   "엣지 케이스 (N과 number가 같은 경우)",
			n:      5,
			number: 5,
			want:   1,
		},
		{
			name:   "엣지 케이스 (8번 넘게 사용해야 하는 경우)",
			n:      5,
			number: 31168, // 5로 31168을 8번 안에 만들기 어려움
			want:   -1,
		},
		{
			name:   "큰 입력 (최댓값 근처 연산)",
			n:      9,
			number: 32000,
			want:   -1, // 혹은 계산 결과에 따른 최솟값
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.n, tt.number)
			if got != tt.want {
				t.Errorf("Solution(%d, %d) = %v, want %v", tt.n, tt.number, got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(number가 크고 조합이 복잡한 경우)
func BenchmarkSolution(b *testing.B) {
	// N이 작고 number가 큰 경우가 보통 탐색 범위가 넓어집니다.
	for i := 0; i < b.N; i++ {
		Solution(1, 32000)
	}
}
