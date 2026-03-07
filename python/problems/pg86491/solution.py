"""
[PG] 86491 - 최소직사각형
https://programmers.co.kr/learn/courses/30/lessons/86491
난이도: lv1
태그: search

시간복잡도: O(n)
공간복잡도: O(1)
"""


def solution(sizes: list[list[int]]) -> int:
    width_max = max(max(w, h) for w, h in sizes)
    high_max = max(min(w, h) for w, h in sizes)

    return  width_max*high_max
