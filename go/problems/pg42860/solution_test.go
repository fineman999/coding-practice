package pg42860

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name  string
		input string
		want  int
	}{
		{
			name:  "기본 케이스",
			input: "JEROEN",
			want:  56,
		},
		{
			name:  "기본 케이스#2",
			input: "JAN",
			want:  23,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.input)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 최악의 케이스(전부 Z, 20글자) 벤치마크
func BenchmarkSolution(b *testing.B) {
	input := "ZZZZZZZZZZZZZZZZZZZZ"
	for i := 0; i < b.N; i++ {
		Solution(input)
	}
}
