"""
[LC] 205 - Isomorphic Strings
https://leetcode.com/problems/isomorphic-strings/
난이도: easy
태그: Hashmap

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from collections import defaultdict
class Solution:

    def isIsomorphic(self, s: str, t: str) -> bool:
        hash_map = defaultdict(str)
        for i in range(len(s)):
            if not hash_map[s[i]] and t[i] in hash_map.values():
                return False
            elif not hash_map[s[i]]:
                hash_map[s[i]] = t[i]
            else:
                if hash_map[s[i]] != t[i]:
                    return False
        return True
