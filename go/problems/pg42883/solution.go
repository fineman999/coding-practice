// Package pg42883 - [PG] 42883 - 큰 수 만들기
// https://programmers.co.kr/learn/courses/30/lessons/42883
// 난이도: lv2
// 태그: greedy
//
// 시간복잡도: O(N)
// 공간복잡도: O(N)
package pg42883

import "strconv"

func Solution(number string, k int) string {
	return solution02(number, k)
}

func solution02(number string, k int) string {
	n := len(number)
	stack := make([]byte, 0, n)

	remainK := k

	for i := 0; i < n; i++ {
		num := number[i]
		for len(stack) > 0 && stack[len(stack)-1] < num && remainK > 0 {
			stack = stack[:len(stack)-1]
			remainK--
		}
		stack = append(stack, num)
	}

	targetLen := n - k
	stack = stack[:targetLen]
	return string(stack)
}

func solution01(number string, k int) string {
	// 1. 스택 생성
	n := len(number)
	stack := make([]int, 0, n)
	pop := func() int {
		top := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		return top
	}
	push := func(element int) {
		stack = append(stack, element)
	}

	peek := func() int {
		if len(stack) < 1 {
			return -1
		}
		return stack[len(stack)-1]
	}

	for i := range number {
		element, _ := strconv.Atoi(string(number[i]))

		for peek() != -1 && peek() < element && k > 0 {
			k--
			pop()
		}
		push(element)
	}

	if k > 0 {
		stack = stack[:len(stack)-k]
	}
	answer := ""
	for i := range stack {
		if i > n-k {
			break
		}
		answer += strconv.Itoa(stack[i])
	}

	return answer
}
