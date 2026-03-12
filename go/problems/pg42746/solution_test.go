package pg42746

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		numbers []int
		want    string
	}{
		{
			name:    "기본 케이스",
			numbers: []int{6, 10, 2},
			want:    "6210", // ❌ 기존 "620" → ✅ "6210" 수정
		},
		{
			name:    "엣지 케이스",
			numbers: []int{3, 30, 34, 5, 9},
			want:    "9534330",
		},
		{
			name:    "모두 0인 경우", // 엣지케이스: "000" → "0"
			numbers: []int{0, 0, 0},
			want:    "0",
		},
		{
			name:    "단일 원소",
			numbers: []int{10},
			want:    "10",
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

// 가장 연산량이 많은 최악의 케이스(큰 입력)
func BenchmarkSolution(b *testing.B) {
	// 제한사항: numbers 길이 최대 100,000 / 원소 최대 1,000
	// 최악의 케이스: 길이 100,000, 원소 모두 1000 (4자리 → 문자열 비교 최대)
	numbers := make([]int, 100_000)
	for i := range numbers {
		numbers[i] = i % 1000
	}

	// 데이터 생성 시간은 벤치마크 측정에서 제외
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		// Solution이 내부에서 슬라이스를 수정하므로 매번 복사본 전달
		b.StopTimer()
		input := make([]int, len(numbers))
		copy(input, numbers)
		b.StartTimer()

		Solution(input)
	}
}
