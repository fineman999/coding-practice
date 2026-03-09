package pg42840

import (
	"algorithm-practice/testutils"
	"reflect"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		answers []int
		want    []int
	}{
		{
			name:    "기본 케이스",
			answers: []int{1, 2, 3, 4, 5},
			want:    []int{1},
		},
		{
			name:    "엣지 케이스",
			answers: []int{1, 3, 2, 4, 2},
			want:    []int{1, 2, 3},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.answers)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {

	answers := testutils.GenerateExpectedAnswer(1, 10_000)
	for i := 0; i < b.N; i++ {
		Solution(answers)
	}
}
