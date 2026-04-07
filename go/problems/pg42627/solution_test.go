package pg42627

import (
	"math/rand"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name string
		jobs [][]int
		want int
	}{
		{
			name: "기본 케이스 (예제)",
			jobs: [][]int{{0, 3}, {1, 9}, {3, 5}},
			want: 8,
		},
		{
			name: "모든 작업이 동시에 요청됨",
			jobs: [][]int{{0, 10}, {0, 3}, {0, 5}},
			want: 9, // (3 + 8 + 18) / 3 = 9.66... -> 9
		},
		{
			name: "작업 사이에 공백이 있는 경우 (엣지 케이스)",
			jobs: [][]int{{0, 3}, {10, 5}, {11, 2}},
			want: 4, // (3 + 5 + 6) / 3 = 4.66... -> 4 (각각 3-0, 15-10, 17-11)
			// 주의: 실제 계산 결과에 따라 want 값은 로직 구현 후 조정이 필요할 수 있습니다.
		},
		{
			name: "작업 소요시간이 모두 같은 경우",
			jobs: [][]int{{0, 5}, {1, 5}, {2, 5}},
			want: 9, // (5 + 9 + 13) / 3 = 9
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solution(tt.jobs)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 가장 연산량이 많은 최악의 케이스(큰 입력: 500개)
func BenchmarkSolution(b *testing.B) {
	// 제한 사항: jobs 길이 500, s/l 1000 이하
	bigJobs := make([][]int, 500)
	for i := 0; i < 500; i++ {
		bigJobs[i] = []int{rand.Intn(1001), rand.Intn(1000) + 1}
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		Solution(bigJobs)
	}
}
