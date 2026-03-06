"""
[PG] 42748 - k번째수
https://programmers.co.kr/learn/courses/30/lessons/42748
난이도: lv1
태그: sort,array

시간복잡도: O(M×NlogN)
공간복잡도: O(N)
"""


def solve(array: list[int], commands: list[list[int]]) -> list[int]:
    return [sorted(array[start-1:end])[index-1] for start, end, index in commands]
