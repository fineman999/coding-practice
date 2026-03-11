package pg12909

import (
	"strings"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name string
		s    string
		want bool
	}{
		{
			name: "기본 케이스 #1",
			s:    "()()",
			want: true,
		},
		{
			name: "기본 케이스 #2",
			s:    "(())()",
			want: true,
		},
		{
			name: "기본 케이스 #3",
			s:    ")()(",
			want: false,
		},
		{
			name: "기본 케이스 #4",
			s:    "(()(",
			want: false,
		},
		{
			name: "큰 입력 정상 케이스",
			s:    strings.Repeat("(", 50000) + strings.Repeat(")", 50000),
			want: true,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.s)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력) 벤치마크
func BenchmarkSolution(b *testing.B) {
	// 길이가 100,000인 올바른 괄호 문자열 생성: "(((((...)))))"
	largeInput := strings.Repeat("(", 50000) + strings.Repeat(")", 50000)

	// 테스트 데이터 생성 시간을 벤치마크 측정 시간에서 제외하기 위해 타이머를 리셋합니다.
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		Solution(largeInput)
	}
}
