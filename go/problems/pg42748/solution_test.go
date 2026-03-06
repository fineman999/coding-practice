package pg42748

import (
	"algorithm-practice/testutils"
	"reflect"
	"testing"
)

func TestSolve(t *testing.T) {
	tests := []struct {
		name     string
		array    []int
		commands [][]int
		want     []int
	}{
		{
			name:     "기본 케이스",
			array:    []int{1, 5, 2, 6, 3, 7, 4},
			commands: [][]int{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}},
			want:     []int{5, 6, 3},
		},
		{
			// 헬퍼 함수를 활용하여 100개의 역순 배열, 50개의 전체 탐색 명령 생성
			name:     "큰 입력 (동적 생성)",
			array:    testutils.GenerateDescendingArray(100),
			commands: testutils.GenerateCommands(50, 1, 100, 50),
			want:     testutils.GenerateExpectedAnswer(50, 50),
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solve(tt.array, tt.commands)
			// 슬라이스 비교는 reflect.DeepEqual을 사용합니다.
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Solve() = %v, want %v", got, tt.want)
			}
		})
	}
}

func BenchmarkSolve(b *testing.B) {
	// 1. 벤치마크에 사용할 가장 무거운(최악의) 입력값을 루프 밖에서 미리 생성합니다.
	// (테스트용 데이터를 만드는 시간은 벤치마크 측정에 포함되면 안 되기 때문입니다.)
	array := testutils.GenerateDescendingArray(100)
	commands := testutils.GenerateCommands(50, 1, 100, 50)

	// 2. 데이터 생성에 걸린 시간을 측정에서 제외하기 위해 타이머를 초기화합니다.
	b.ResetTimer()

	// 3. b.N번 반복 실행하며 실제 함수의 성능만 순수하게 측정합니다.
	for i := 0; i < b.N; i++ {
		Solve(array, commands)
	}
}
