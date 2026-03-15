package pg43165

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		numbers []int
		target  int
		want    int
	}{
		{
			name:    "입출력 예시 #1",
			numbers: []int{1, 1, 1, 1, 1},
			target:  3,
			want:    5,
		},
		{
			name:    "입출력 예시 #2",
			numbers: []int{4, 1, 2, 1},
			target:  4,
			want:    2,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.numbers, tt.target)
			if got != tt.want {
				t.Errorf("Solution(%v, %d) = %v, want %v", tt.numbers, tt.target, got, tt.want)
			}
		})
	}
}

// "가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {
	// numbers의 최댓값 길이는 20 (프로그래머스 제한)
	numbers := make([]int, 20)
	for i := range numbers {
		numbers[i] = 1
	}
	target := 10
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		Solution(numbers, target)
	}
}
