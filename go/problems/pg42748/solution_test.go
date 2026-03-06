package pg42748

import "testing"

func TestSolve(t *testing.T) {
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
			got := Solve()
			if got != tt.want {
				t.Errorf("Solve() = %v, want %v", got, tt.want)
			}
		})
	}
}

func BenchmarkSolve(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Solve()
	}
}
