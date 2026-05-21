"""
[PG] 1845 - 폰켓몬
https://programmers.co.kr/learn/courses/30/lessons/1845
난이도: lv1
태그: hash

시간복잡도: O(N)
공간복잡도: O(N)
"""

import sys


input_data = sys.stdin.readline

from collections import defaultdict

def solution(nums):
    num_map = defaultdict(int)
    for num in nums:
        num_map[num] += 1
    return min(len(num_map), len(nums)//2)


def solution02(nums):
    return min(len(nums)/2, len(set(nums)))

def solve():
    n = int(input_data().strip())
    nums = list(map(int, input_data().split()))
    
    solution(nums)


if __name__ == "__main__":
    solve()