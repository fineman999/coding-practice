"""
[PG] 42842 - 완전탐색
https://programmers.co.kr/learn/courses/30/lessons/42842
난이도: lv2
태그: search

시간복잡도: O(sqrt(n))
공간복잡도: O(1)
"""
import math

def solution(brown: int, yellow: int):
    total_area = brown + yellow

    # 세로(h)를 3부터 루트값까지 탐색
    for h in range(3, int(math.sqrt(total_area)) + 1):
        if total_area % h == 0:
            w = total_area // h

            # 노란색 격자 수 조건 확인
            if (w - 2) * (h - 2) == yellow:
                return [w, h]

    return []
