package testutils

// GenerateDescendingArray는 n부터 1까지 감소하는 크기 n의 배열을 생성합니다.
// 최악의 정렬 케이스를 만들 때 유용합니다.
func GenerateDescendingArray(n int) []int {
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		arr[i] = n - i
	}
	return arr
}

// GenerateCommands는 동일한 [i, j, k] 명령을 m번 반복하는 2차원 배열을 생성합니다.
func GenerateCommands(m int, i, j, k int) [][]int {
	cmds := make([][]int, m)
	for x := 0; x < m; x++ {
		cmds[x] = []int{i, j, k}
	}
	return cmds
}

// GenerateExpectedAnswer는 동일한 정답 val을 m번 반복하는 배열을 생성합니다.
func GenerateExpectedAnswer(m int, val int) []int {
	ans := make([]int, m)
	for i := 0; i < m; i++ {
		ans[i] = val
	}
	return ans
}
