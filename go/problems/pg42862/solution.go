// Package pg42862 - [PG] 42862 - 체육복
// https://programmers.co.kr/learn/courses/30/lessons/42862
// 난이도: lv1
// 태그: greedy
//
// 시간복잡도: O(n)
// 공간복잡도: O(n)
package pg42862

func Solution(n int, lost []int, reserve []int) int {
	people := make([]int, n)
	for i := range lost {
		people[lost[i]-1]--
	}
	for i := range reserve {
		people[reserve[i]-1]++
	}
	answer := 0
	for i := range people {
		if people[i] < 0 {
			if i-1 >= 0 && people[i-1] > 0 {
				people[i-1]--
				people[i]++
			} else if i+1 < n && people[i+1] > 0 {
				people[i+1]--
				people[i]++
			}
		}
	}
	for i := range people {
		if people[i] >= 0 {
			answer++
		}
	}
	return answer
}
