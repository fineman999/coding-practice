// Package lc392 - [LC] 392 - Is Subsequence
// https://leetcode.com/problems/is-subsequence/
// 난이도: easy
// 태그: Two Pointers
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc392

func isSubsequence(s string, t string) bool {
	sPointer := 0
	for i := 0; i < len(t); i++ {
		if sPointer < len(s) && s[sPointer] == t[i] {
			sPointer++
		}
	}
	return sPointer == len(s)
}
