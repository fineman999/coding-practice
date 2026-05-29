// Package lc58 - [LC] 58 - Length of Last Word
// https://leetcode.com/problems/length-of-last-word/
// 난이도: easy
// 태그: array
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc58

import "strings"

func lengthOfLastWord(s string) int {
	words := strings.Fields(s)
	return len(words[len(words)-1])
}
