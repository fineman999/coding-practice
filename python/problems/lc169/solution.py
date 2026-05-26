"""
[LC] 169 - Majority Element
https://leetcode.com/problems/majority-element/
난이도: easy
태그: array

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from typing import List

class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        count = 0
        answer = 0
        for num in nums:
            if count == 0:
                answer = num
            if answer == num:
                count += 1
            else:
                count -= 1
        return answer

