// Package lc13 - [LC] 13 - Roman to Integer
// https://leetcode.com/problems/roman-to-integer/
// 난이도: easy
// 태그: array
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc13

var dictionary = map[rune]int{
	'I': 1,
	'V': 5,
	'X': 10,
	'L': 50,
	'C': 100,
	'D': 500,
	'M': 1000,
}

func romanToInt(s string) int {
	answer := 0

	for i := range s {
		if len(s) > i && dictionary[rune(s[i])] < dictionary[rune(s[i+1])] {
			answer -= dictionary[rune(s[i])]
		} else {
			answer += dictionary[rune(s[i])]
		}
	}
	return answer
}
