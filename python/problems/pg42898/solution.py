"""
[PG] 42898 - 등굣길
https://programmers.co.kr/learn/courses/30/lessons/42898
난이도: lv3
태그: DP

시간복잡도: O(N^2)
공간복잡도: O(1)
"""


def solution(m: int, n: int, puddles: list[list[int]]) -> int:
    # 1. 초기화
    graph = [[0]*m for _ in range(n)]
    graph[0][0] = 1
    for puddle in puddles:
        x, y = puddle[0], puddle[1]
        graph[y-1][x-1] = -1
    for y in range(n):
        for x in range(m):
            if graph[y][x] == 0:
                up, left = 0, 0
                if x > 0 and graph[y][x - 1] != -1:
                    left = graph[y][x-1]
                if y > 0 and graph[y - 1][x] != -1:
                    up = graph[y-1][x]
                graph[y][x] += left + up
    return graph[n-1][m-1] % 1_000_000_007
