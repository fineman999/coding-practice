"""
[LC] 13 - Roman to Integer
https://leetcode.com/problems/roman-to-integer/
난이도: easy
태그: array

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
#
# I             1
# V             5
# X             10
# L             50
# C             100
# D             500
# M             1000
# I can be placed before V (5) and X (10) to make 4 and 9.
# X can be placed before L (50) and C (100) to make 40 and 90.
# C can be placed before D (500) and M (1000) to make 400 and 900.
dictionary = {
    'I':1,
    'V':5,
    'X':10,
    'L': 50,
    'C': 100,
    'D': 500,
    'M':1000
}
class Solution:
    def romanToInt(self, s: str) -> int:
        answer = 0
        for i in range(len(s)-1,-1,-1):
            if s[i] == 'I':
                if i+1 < len(s) and (s[i+1] == 'V' or s[i+1] == 'X'):
                    answer -= dictionary[s[i]]
                else:
                    answer += dictionary[s[i]]
            elif s[i] == 'X':
                if i+1 < len(s) and (s[i+1] == 'L' or s[i+1] == 'C'):
                    answer -= dictionary[s[i]]
                else:
                    answer += dictionary[s[i]]
            elif s[i] == 'C':
                if  i+1 < len(s) and (s[i+1] == 'D' or s[i+1] == 'M'):
                    answer -= dictionary[s[i]]
                else:
                    answer += dictionary[s[i]]
            else:
                answer += dictionary[s[i]]
        return answer



