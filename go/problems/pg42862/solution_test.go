package pg42862

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name string
		want int
	}{
		{
			name: "기본 케이스",
			want: 0,
		},
		{
			name: "엣지 케이스",
			want: 0,
		},
		{
			name: "큰 입력",
			want: 0,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution()
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Solution()
	}
}
