"""
[PG] 87946 - 피로도
https://programmers.co.kr/learn/courses/30/lessons/87946
난이도: lv2
태그: search

시간복잡도: O(N!)
공간복잡도: O(N)
"""
from copy import deepcopy


def solution(k, dungeons) -> int:
    is_valid = [False] * len(dungeons)
    answer = recursive(is_valid, dungeons, k, 0)

    return answer


def recursive(is_valid: list[bool], dungeons: list[int], rest_k: int, count: int):
    max_count = count
    for i in range(len(dungeons)):
        if not is_valid[i] and rest_k >= dungeons[i][0]:
            is_valid[i] = True
            max_count = max(recursive(is_valid, dungeons, rest_k - dungeons[i][1], count + 1), max_count)
            is_valid[i] = False
    return max_count
