package pg42883

import (
	"strings"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		number string
		k      int
		want   string
	}{
		{
			name:   "기본 케이스 1",
			number: "1924",
			k:      2,
			want:   "94",
		},
		{
			name:   "기본 케이스 2",
			number: "1231234",
			k:      3,
			want:   "3234",
		},
		{
			name:   "기본 케이스 3",
			number: "4177252841",
			k:      4,
			want:   "775841",
		},
		{
			name:   "엣지 케이스: 내림차순 (뒤를 잘라야 함)",
			number: "9876",
			k:      2,
			want:   "98",
		},
		{
			name:   "엣지 케이스: 모든 숫자가 같음",
			number: "1111",
			k:      2,
			want:   "11",
		},
		{
			name:   "엣지 케이스: 한 자리만 남기기",
			number: "10",
			k:      1,
			want:   "1",
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.number, tt.k)
			if got != tt.want {
				t.Errorf("Solution(%v, %v) = %v, want %v", tt.number, tt.k, got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스 (1,000,000자리)
func BenchmarkSolution(b *testing.B) {
	// 1이 50만개, 2가 50만개인 100만 자리 숫자 생성
	number := strings.Repeat("1", 500000) + strings.Repeat("2", 500000)
	k := 500000

	b.ResetTimer() // 셋업 시간 제외
	for i := 0; i < b.N; i++ {
		Solution(number, k)
	}
}
