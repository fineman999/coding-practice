// Package pg42748 - [PG] 42748 - k번째수
// https://programmers.co.kr/learn/courses/30/lessons/42748
// 난이도: lv1
// 태그: sort,array
//
// 시간복잡도: O(nlogn)
// 공간복잡도: O(n)
package pg42748

import "sort"

func Solve(array []int, commands [][]int) []int {
	answer := make([]int, 0, len(commands))

	for _, cmd := range commands {
		tempAnswer := make([]int, cmd[1]-(cmd[0]-1))
		copy(tempAnswer, array[cmd[0]-1:cmd[1]])

		// 다음과 같이 사용하면 append의 특성으로 추가할때마다 기존 메모리가 두배 크기로 사용된다.
		// 그러므로 메모리 할당이 낭비된다.
		//tempAnswer := []int{}
		//for _, element := range array[cmd[0]-1 : cmd[1]] {
		//	tempAnswer = append(tempAnswer, element)
		//}

		sort.Ints(tempAnswer)
		answer = append(answer, tempAnswer[cmd[2]-1])
	}

	return answer
}
