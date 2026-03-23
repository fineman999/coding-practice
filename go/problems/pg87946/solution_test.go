package pg87946

import "testing"

func TestSolution(t *testing.T) {
	tests := []struct {
		name     string
		k        int
		dungeons [][]int
		want     int
	}{
		{
			name:     "기본 케이스",
			k:        80,
			dungeons: [][]int{{80, 20}, {50, 40}, {30, 10}},
			want:     3,
		},
		{
			name:     "피로도 부족 케이스",
			k:        10,
			dungeons: [][]int{{80, 20}, {50, 40}},
			want:     0,
		},
		{
			name:     "순서가 중요한 케이스",
			k:        40,
			dungeons: [][]int{{40, 40}, {10, 10}, {10, 10}},
			want:     2, // (10,10) 두 개를 먼저 도는 게 이득
		},
		{
			name: "던전 8개 최대 입력 케이스",
			k:    5000,
			dungeons: [][]int{
				{1, 1}, {1, 1}, {1, 1}, {1, 1},
				{1, 1}, {1, 1}, {1, 1}, {1, 1},
			},
			want: 8,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.k, tt.dungeons)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스 (8! 경로 탐색)
func BenchmarkSolution(b *testing.B) {
	k := 5000
	dungeons := [][]int{
		{100, 10}, {100, 10}, {100, 10}, {100, 10},
		{100, 10}, {100, 10}, {100, 10}, {100, 10},
	}

	b.ResetTimer() // 타이머 초기화
	for i := 0; i < b.N; i++ {
		Solution(k, dungeons)
	}
}
