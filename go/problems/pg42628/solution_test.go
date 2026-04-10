package pg42628

import (
	"reflect"
	"testing"
)

func TestSolution(t *testing.T) {
	tests := []struct {
		name       string
		operations []string
		want       []int
	}{
		{
			name:       "기본 케이스 1",
			operations: []string{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
			want:       []int{0, 0},
		},
		{
			name:       "기본 케이스 2",
			operations: []string{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"},
			want:       []int{333, -45},
		},
		{
			name:       "엣지 케이스: 빈 큐 삭제",
			operations: []string{"D 1", "D -1", "I 5", "D 1", "D 1"},
			want:       []int{0, 0},
		},
		{
			name:       "엣지 케이스: 동일 값 중복",
			operations: []string{"I 10", "I 10", "I 10", "D 1"},
			want:       []int{10, 10},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Solution 함수가 []string을 인자로 받는다고 가정합니다.
			got := Solution(tt.operations)
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Solution() = %v, want %v", got, tt.want)
			}
		})
	}
}

// 성능 측정을 위한 벤치마크 (최악의 케이스 시뮬레이션)
func BenchmarkSolution(b *testing.B) {
	// 약 10,000개의 연산 생성
	ops := make([]string, 10000)
	for i := 0; i < 5000; i++ {
		ops[i] = "I 1"
		ops[i+5000] = "D 1"
	}

	b.ResetTimer() // 준비 시간 제외
	for i := 0; i < b.N; i++ {
		Solution(ops)
	}
}
