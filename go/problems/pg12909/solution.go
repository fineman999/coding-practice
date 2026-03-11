// Package pg12909 - [PG] 12909 - 올바른 괄호
// https://programmers.co.kr/learn/courses/30/lessons/12909
// 난이도: lv2
// 태그: stack,queue
//
// 시간복잡도: O(n)
// 공간복잡도: O(1)
package pg12909

func Solution(s string) bool {
	count := 0
	for i := range s {
		if s[i] == '(' {
			count++
		} else {
			if count == 0 {
				return false
			}
			count--
		}
	}
	return count == 0
}
