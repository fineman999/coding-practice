"""
[LC] 383 - Ransom Note
https://leetcode.com/problems/ransom-note/
난이도: easy
태그: Hashmap

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from collections import defaultdict

class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        hash_map = defaultdict(int)
        for i in range(len(magazine)):
            hash_map[magazine[i]] +=1

        for i in range(len(ransomNote)):
            if hash_map[ransomNote[i]] == 0:
                return False
            hash_map[ransomNote[i]] -= 1
        return True

