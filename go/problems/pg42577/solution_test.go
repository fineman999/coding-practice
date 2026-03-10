package pg42577

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name      string
		phoneBook []string
		want      bool
	}{
		{
			name:      "기본 케이스",
			phoneBook: []string{"119", "97674223", "1195524421"},
			want:      false,
		},
		{
			name:      "기본케이스 #2",
			phoneBook: []string{"123", "456", "789"},
			want:      true,
		},
		{
			name:      "기본케이스 #3",
			phoneBook: []string{"12", "123", "1235", "567", "88"},
			want:      false,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.phoneBook)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)"
// 가장 연산량이 많은 최악의 케이스(큰 입력)"
func BenchmarkSolution(b *testing.B) {
	// 10,000개의 일치하지 않는 전화번호 세팅 (1000000 ~ 1009999)
	var worstCase []string
	for i := 0; i < 10000; i++ {
		// "1000000", "1000001" 등 문자열 생성
		worstCase = append(worstCase, string(rune(1000000+i)))
	}

	b.ResetTimer() // 데이터 세팅 시간은 벤치마크 측정에서 제외

	for i := 0; i < b.N; i++ {
		// 슬라이스를 복사해서 넘겨야 정렬 시 원본이 유지됨
		temp := make([]string, len(worstCase))
		copy(temp, worstCase)

		Solution(temp)
	}
}
