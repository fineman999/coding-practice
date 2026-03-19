package pg42584

import (
	"reflect"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		prices []int
		want   []int
	}{
		{
			name:   "기본 입출력 예시",
			prices: []int{1, 2, 3, 2, 3},
			want:   []int{4, 3, 1, 1, 0},
		},
		{
			name:   "계속 상승하는 경우",
			prices: []int{1, 2, 3, 4, 5},
			want:   []int{4, 3, 2, 1, 0},
		},
		{
			name:   "가격이 모두 같은 경우",
			prices: []int{2, 2, 2, 2, 2},
			want:   []int{4, 3, 2, 1, 0},
		},
		{
			name:   "단일 원소 (최소 길이 2 미만은 제한사항 외지만 체크)",
			prices: []int{1},
			want:   []int{0},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.prices)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 성능 측정을 위한 벤치마크 코드
func BenchmarkSolution(b *testing.B) {
	// 제한사항: prices 길이 최대 100,000
	// 최악의 케이스: 계속 가격이 상승하거나 하락하여 스택 연산이 최대인 경우
	size := 100_000
	prices := make([]int, size)
	for i := 0; i < size; i++ {
		prices[i] = i + 1
	}

	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		// 원본 배열 보존을 위해 복사본 사용 (필요시)
		input := make([]int, len(prices))
		copy(input, prices)

		Solution(input)
	}
}
