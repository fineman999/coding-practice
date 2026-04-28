"""
[PG] 43105 - 정수 삼각형
https://programmers.co.kr/learn/courses/30/lessons/43105
난이도: lv3
태그: DP

시간복잡도: O(N^2)
공간복잡도: O(1)
"""


def solution(triangle: list[list[int]]) -> int:
    for y in range(len(triangle) -2, -1, -1):
        for x in range(len(triangle[y])):
            triangle[y][x] += max(triangle[y+1][x], triangle[y+1][x+1])

    return triangle[0][0]
