"""
[LC] 392 - Is Subsequence
https://leetcode.com/problems/is-subsequence/
난이도: easy
태그: Two Pointers

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:

        check = 0
        for i in range(len(s)):
            if check < len(s) and s[check] == t[i]:
                check += 1
        if check == len(s):
            return True
        return False
