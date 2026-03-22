package pg42842

import (
	"reflect"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		brown  int
		yellow int
		want   []int
	}{
		{
			name:   "입출력 예 1",
			brown:  10,
			yellow: 2,
			want:   []int{4, 3},
		},
		{
			name:   "입출력 예 2",
			brown:  8,
			yellow: 1,
			want:   []int{3, 3},
		},
		{
			name:   "입출력 예 3",
			brown:  24,
			yellow: 24,
			want:   []int{8, 6},
		},
		{
			name:   "엣지 케이스 (가로가 긴 경우)",
			brown:  18,
			yellow: 6,
			want:   []int{8, 3},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Solution 함수가 brown, yellow 두 인자를 받도록 구현해야 합니다.
			got := Solution(tt.brown, tt.yellow)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: 가장 연산량이 많은 최악의 케이스
func BenchmarkSolution(b *testing.B) {
	// brown 5000, yellow 2,000,000 범위 내의 큰 값 예시
	brown := 4004
	yellow := 1000000

	b.ResetTimer() // 초기화 시간 제외
	for i := 0; i < b.N; i++ {
		Solution(brown, yellow)
	}
}
