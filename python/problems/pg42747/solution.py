"""
[PG] 42747 - H-index
https://programmers.co.kr/learn/courses/30/lessons/42747
난이도: lv2
태그: sort

시간복잡도: O(N)
공간복잡도: O(N)
"""
from collections import deque
from typing import Any


def solution(citations: list[int]):
    citations.sort(reverse=True)
    return max(map(min,enumerate(citations, 1)), default=0)


def solution01(citations: list[int]) -> int | Any:
    answer = 0
    rest_cnt = 0
    citations.sort(reverse=True)
    left = deque(citations)
    max_cnt = max(citations)
    for i in range(max_cnt + 1):
        while left and left[-1] < i:
            left.pop()
            rest_cnt += 1
        if len(left) >= i >= rest_cnt:
            answer = max(answer, i)
    return answer