// Package lc383 - [LC] 383 - Ransom Note
// https://leetcode.com/problems/ransom-note/
// 난이도: easy
// 태그: Hashmap
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc383

func canConstruct(ransomNote string, magazine string) bool {
	hashMap := make(map[rune]int)
	// range를 쓰면 ch는 자동으로 올바른 rune 타입이 됩니다.
	for _, ch := range magazine {
		hashMap[ch] += 1
	}

	for _, ch := range ransomNote {
		if hashMap[ch] == 0 {
			return false
		}
		hashMap[ch] -= 1
	}
	return true
}
