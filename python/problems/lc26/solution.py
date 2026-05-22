"""
[LC] 26 - Remove Duplicates
https://leetcode.com/problems/remove-duplicates/
난이도: easy
태그: array,two-pointers

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        left = 0
        for right in range(1, len(nums)):
            if nums[left] != nums[right]:
                left += 1
                nums[left] = nums[right]

        return left+1

