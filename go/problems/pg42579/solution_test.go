package pg42579

import (
	"reflect"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name   string
		genres []string
		plays  []int
		want   []int
	}{
		{
			name:   "기본 케이스",
			genres: []string{"classic", "pop", "classic", "classic", "pop"},
			plays:  []int{500, 600, 150, 800, 2500},
			want:   []int{4, 1, 3, 0},
		},
		{
			name:   "장르 내 곡이 하나인 경우",
			genres: []string{"classic", "pop", "jazz"},
			plays:  []int{500, 600, 3000},
			want:   []int{2, 1, 0},
		},
		{
			name:   "재생 횟수가 같을 때 고유 번호 우선순위",
			genres: []string{"pop", "pop", "pop"},
			plays:  []int{500, 500, 500},
			want:   []int{0, 1},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.genres, tt.plays)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 벤치마크: 최악의 케이스 (데이터 10,000개)
func BenchmarkSolution(b *testing.B) {
	// 데이터 준비 (Setup)
	genres := make([]string, 10000)
	plays := make([]int, 10000)
	for i := 0; i < 10000; i++ {
		genres[i] = "genre" + string(rune(i%100))
		plays[i] = i
	}

	b.ResetTimer() // 준비 시간은 측정에서 제외
	for i := 0; i < b.N; i++ {
		Solution(genres, plays)
	}
}
