// Package pg42885 - [PG] 42885 - 구명보트
// https://programmers.co.kr/learn/courses/30/lessons/42885
// 난이도: lv2
// 태그: greedy
//
// 시간복잡도: O(NLogN)
// 공간복잡도: O(1)
package pg42885

import "sort"

func Solution(people []int, limit int) int {

	sort.Sort(sort.Reverse(sort.IntSlice(people)))
	left, right := 0, len(people)-1

	answer := 0
	for left <= right {
		leftPerson := people[left]

		if leftPerson+people[right] <= limit {
			right--
		}

		left++
		answer++
	}
	return answer
}
