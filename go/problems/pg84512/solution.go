// Package pg84512 - [PG] 84512 - 모음사진
// https://programmers.co.kr/learn/courses/30/lessons/84512
// 난이도: lv2
// 태그: search
//
// 시간복잡도: O(5^n)
// 공간복잡도: O(n) (n= 길이)
package pg84512

func Solution(words string) int {

	alpha := []string{"A", "E", "I", "O", "U"}
	count, n := 0, 5
	var recursive func(string) bool

	recursive = func(currWord string) bool {
		if currWord == words {
			return true
		}
		if len(currWord) == n {
			return false
		}

		for i := range alpha {
			count++
			if recursive(currWord + alpha[i]) {
				return true
			}
		}
		return false
	}

	recursive("")
	return count

}
