"""
[PG] 42840 - 모의고사
https://programmers.co.kr/learn/courses/30/lessons/42840
난이도: lv1
태그: sort

시간복잡도: O(n)
공간복잡도: O(1)
"""
from itertools import cycle

PATTERNS = [
    [1, 2, 3, 4, 5],
    [2, 1, 2, 3, 2, 4, 2, 5],
    [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
]


def solution(answers: list[int]) -> list[int]:
    scores = [
        sum(p == a for p, a in zip(cycle(pattern), answers))
        for pattern in PATTERNS
    ]
    max_score = max(scores)
    return [i + 1 for i, score in enumerate(scores) if max_score == score]
