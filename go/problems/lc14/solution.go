// Package lc14 - [LC] 14 - Longest Common Prefix
// https://leetcode.com/problems/longest-common-prefix/
// 난이도: easy
// 태그: array
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc14

import "sort"

func longestCommonPrefix(strs []string) string {
	if len(strs) == 1 {
		return strs[0]
	}
	sort.Strings(strs)

	first := strs[0]
	last := strs[len(strs)-1]
	for i := 0; i < len(first); i++ {
		if first[i] != last[i] {
			return first[:i]
		}
	}
	return first
}
