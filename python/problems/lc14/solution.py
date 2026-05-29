"""
[LC] 14 - Longest Common Prefix
https://leetcode.com/problems/longest-common-prefix/
난이도: easy
태그: array

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from typing import List


class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if len(strs) == 1:
            return strs[0]

        strs.sort()
        first = strs[0]
        last = strs[-1]
        answer = ""
        for i in range(len(first)):
            if first[i] == last[i]:
                answer += first[i]
            else:
                break
        return answer

