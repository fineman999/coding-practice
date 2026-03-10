package pg42578

import (
	"strconv"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name    string
		clothes [][]string
		want    int
	}{
		{
			name:    "기본 케이스",
			clothes: [][]string{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}},
			want:    5,
		},
		{
			name:    "기본 케이스 #2",
			clothes: [][]string{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}},
			want:    3,
		},
		{
			name:    "기본 케이스 #3",
			clothes: [][]string{{"crow_mask", "face"}, {"blue_sunglasses", "GO"}, {"smoky_makeup", "THE"}},
			want:    7,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.clothes)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력)
func BenchmarkSolution(b *testing.B) {
	// 옷의 종류가 모두 다른 최악의 케이스 세팅 (Map에 Key가 계속 추가되어야 함)
	// (주의: 조합의 수가 커지면 int 범위를 초과할 수 있지만, 여기선 성능 측정만 하므로 무시합니다)
	var worstCase [][]string
	for i := 0; i < 1000; i++ {
		// "type0", "type1" ... 같이 고유한 종류(Category) 생성
		category := "type" + strconv.Itoa(i)
		worstCase = append(worstCase, []string{"clothName", category})
	}

	b.ResetTimer() // 데이터 세팅 시간 제외

	for i := 0; i < b.N; i++ {
		Solution(worstCase)
	}
}
