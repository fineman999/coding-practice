// Package lc169 - [LC] 169 - Majority Element
// https://leetcode.com/problems/majority-element/
// 난이도: easy
// 태그: array
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc169

func majorityElement(nums []int) int {
	answer := 0
	count := 0

	for i := range nums {
		if count == 0 {
			answer = nums[i]
		}
		if nums[i] == answer {
			count++
		} else {
			count--
		}
	}
	return answer
}
