// Package pg42584 - [PG] 42584 - 주식가격
// https://programmers.co.kr/learn/courses/30/lessons/42584
// 난이도: lv2
// 태그: stack,queue
//
// 시간복잡도: O(n^2)/스택 O(n)
// 공간복잡도: O(N)
package pg42584

func Solution(prices []int) []int {
	return solution01(prices)
}

func solution01(prices []int) []int {
	answers := make([]int, len(prices))
	n := len(prices)
	stack := make([]int, 0, len(prices))
	for i, price := range prices {
		for len(stack) > 0 && prices[stack[len(stack)-1]] > price {
			top := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			answers[top] = i - top
		}
		stack = append(stack, i)
	}

	for len(stack) > 0 {
		top := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		answers[top] = n - 1 - top
	}
	return answers
}

func solution02(prices []int) []int {
	answers := make([]int, len(prices))
	n := len(prices)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			answers[i]++
			if prices[i] > prices[j] {
				break
			}
		}
	}
	return answers
}
