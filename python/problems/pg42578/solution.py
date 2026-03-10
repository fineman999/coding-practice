"""
[PG] 42578 - 의상
https://programmers.co.kr/learn/courses/30/lessons/42578
난이도: lv2
태그: hash

시간복잡도: O(n)
공간복잡도: O(n)
"""
import math
from collections import Counter


def solution(clothes: list[list[str]]) -> int:
    counter = Counter([count for _, count in clothes])
    return math.prod( value + 1 for value in counter.values()) - 1
