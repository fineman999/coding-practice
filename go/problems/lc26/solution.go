// Package lc26 - [LC] 26 - Remove Duplicates
// https://leetcode.com/problems/remove-duplicates/
// 난이도: easy
// 태그: array,two-pointers
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc26

func removeDuplicates(nums []int) int {

	left := 0
	for i := range nums {
		if nums[left] != nums[i] {
			left++
			nums[left] = nums[i]
		}
	}

	return left + 1

}
