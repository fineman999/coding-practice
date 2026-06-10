// Package lc205 - [LC] 205 - Isomorphic Strings
// https://leetcode.com/problems/isomorphic-strings/
// 난이도: easy
// 태그: Hashmap
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc205

func isIsomorphic(s string, t string) bool {
	// 문자열을 rune 슬라이스로 변환하여 멀티바이트 문자 깨짐을 방지합니다.
	sRunes := []rune(s)
	tRunes := []rune(t)
	usedValues := make(map[rune]bool)
	hashMap := make(map[rune]rune)
	for i, ch := range sRunes {
		tChar := tRunes[i]
		val, ok := hashMap[ch]
		if !ok {
			if usedValues[tChar] {
				return false
			}
			usedValues[tChar] = true
			hashMap[ch] = tChar
		} else {
			if val != tChar {
				return false
			}
		}
	}
	return true
}
