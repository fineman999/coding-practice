package pg84512

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name string
		word string
		want int
	}{
		{
			name: "기본 케이스 1",
			word: "AAAAE",
			want: 6,
		},
		{
			name: "기본 케이스 2",
			word: "AAAE",
			want: 10,
		},
		{
			name: "기본 케이스 3",
			word: "I",
			want: 1563,
		},
		{
			name: "기본 케이스 4",
			word: "EIO",
			want: 1189,
		},
		{
			name: "엣지 케이스 (첫 단어)",
			word: "A",
			want: 1,
		},
		{
			name: "엣지 케이스 (마지막 단어)",
			word: "UUUUU",
			want: 3905,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.word)
			if got != tt.want {
				t.Errorf("Solution(%v) = %v, want %v", tt.word, got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(마지막 단어 "UUUUU")로 성능 측정
func BenchmarkSolution(b *testing.B) {
	word := "UUUUU"
	for i := 0; i < b.N; i++ {
		Solution(word)
	}
}
