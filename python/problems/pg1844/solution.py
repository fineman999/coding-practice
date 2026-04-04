"""
[PG] 1844 - 게임 맵 최단거리
https://programmers.co.kr/learn/courses/30/lessons/1844
난이도: lv2
태그: dfs, bfs

시간복잡도: O(n*m)
공간복잡도: O(n*m)
"""
from collections import deque

def solution(maps: list[list[int]]) -> int:
    n = len(maps)
    m = len(maps[0])
    # set up
    queue = deque([(0,0)])

    xs = [1,0,-1,0]
    ys = [0,1,0,-1]
    while queue:
        x, y = queue.popleft()

        for i in range(4):
            next_x = x + xs[i]
            next_y = y + ys[i]
            if 0 <= next_x < n and 0 <= next_y < m and maps[next_x][next_y] == 1:
                queue.append((next_x, next_y))
                maps[next_x][next_y] = maps[x][y] + 1
    if maps[n-1][m-1] == 1:
        return -1
    return maps[n-1][m-1]



