package pg42583

import (
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name         string
		bridgeLength int
		weight       int
		truckWeights []int
		want         int
	}{
		{
			name:         "기본 케이스 1",
			bridgeLength: 2,
			weight:       10,
			truckWeights: []int{7, 4, 5, 6},
			want:         8,
		},
		{
			name:         "기본 케이스 2",
			bridgeLength: 100,
			weight:       100,
			truckWeights: []int{10},
			want:         101,
		},
		{
			name:         "기본 케이스 3",
			bridgeLength: 100,
			weight:       100,
			truckWeights: []int{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
			want:         110,
		},
		{
			name:         "엣지 케이스 (최소 입력)",
			bridgeLength: 1,
			weight:       10,
			truckWeights: []int{7},
			want:         2,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Solution 함수가 bridgeLength, weight, truckWeights를 인자로 받는다고 가정
			got := Solution(tt.bridgeLength, tt.weight, tt.truckWeights)
			if got != tt.want {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// BenchmarkSolution: 가장 연산량이 많은 최악의 케이스 (트럭 1만 대, 다리 길이 1만)
func BenchmarkSolution(b *testing.B) {
	bridgeLength := 10000
	weight := 10000
	// 모든 트럭이 무게 1이라서 다리 길이만큼 꽉 차서 한 대씩 밀려 나가는 케이스
	truckWeights := make([]int, 10000)
	for i := range truckWeights {
		truckWeights[i] = 1
	}

	b.ResetTimer() // 데이터 준비 시간은 제외
	for i := 0; i < b.N; i++ {
		// 원본 슬라이스가 변경되지 않도록 복사본 전달 (Solution 내부 로직에 따라 선택)
		tempTrucks := make([]int, len(truckWeights))
		copy(tempTrucks, truckWeights)
		Solution(bridgeLength, weight, tempTrucks)
	}
}
