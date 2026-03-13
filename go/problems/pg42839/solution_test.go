package pg42839

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		numbers string
		want    int
	}{
		{
			name:    "기본 케이스",
			numbers: "17",
			want:    3,
		},
		{
			name:    "엣지 케이스",
			numbers: "011",
			want:    2,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.numbers)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"

// 큰 입력 벤치마크
func BenchmarkSolution(b *testing.B) {
	cases := []string{
		"11117",
		"9876543",
		"1234567",
	}
	for _, input := range cases {
		b.Run(input, func(b *testing.B) {
			for i := 0; i < b.N; i++ {
				Solution(input)
			}
		})
	}
}
