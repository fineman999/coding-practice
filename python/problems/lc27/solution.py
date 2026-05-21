"""
[LC] 27 - Remove Element
https://leetcode.com/problems/remove-element/
난이도: easy
태그: array,two-pointers

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from typing import List


class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        k = 0
        for i in range(len(nums)):
            if nums[i] != val:
                nums[k] = nums[i]
                k += 1
        return k

