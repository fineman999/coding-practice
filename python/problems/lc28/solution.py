"""
[LC] 28 - Find the Index of the First Occurrence in a String
https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
난이도: easy
태그: array

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        for i in range(len(haystack)):
            valid = True
            for j in range(len(needle)):
                if i+j >= len(haystack) or haystack[i+j] != needle[j]:
                    valid = False
            if valid:
                return i
        return -1
