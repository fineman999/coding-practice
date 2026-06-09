// Package lc28 - [LC] 28 - Find the Index of the First Occurrence in a String
// https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
// 난이도: easy
// 태그: array
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc28

func strStr(haystack string, needle string) int {
	if len(needle) == 0 {
		return -1
	}
	lps := make([]int, len(needle))
	prevLPS := 0
	i := 0
	for i < len(needle) {
		if needle[i] == needle[prevLPS] {
			lps[i] = prevLPS + 1
			prevLPS++
			i++
		} else if prevLPS == 0 {
			lps[i] = 0
			i++
		} else {
			prevLPS = lps[prevLPS-1]
		}
	}
	i = 0
	j := 0
	for i < len(haystack) {
		if haystack[i] == needle[j] {
			i++
			j++
		} else if j == 0 {
			i++
		} else {
			j = lps[j-1]
		}
		if j == len(needle) {
			return i - j
		}
	}
	return -1
}
