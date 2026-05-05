"""
[PG] 87694 - 아이템 줍기
https://programmers.co.kr/learn/courses/30/lessons/87694
난이도: lv3
태그: bfs,graph,geometry

시간복잡도: O(N * N) 좌표크기
공간복잡도: O(N * N) 좌표크기
"""
import sys
from collections import  deque
input_data = sys.stdin.readline

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def solve():
    n = int(input().strip())
    rectangle = []
    for _ in range(n):
        x1, y1, x2, y2 = map(int, input_data().split())
        rectangle.append([x1, y1, x2, y2])

    characterX, characterY, itemX, itemY = map(int, input_data().split())

    answer = solution(rectangle, characterX, characterY, itemX, itemY)
    print(answer)

def solution(rectangle: list[list], characterX: int, characterY: int, itemX: int, itemY: int) -> int:
    n = 51*2
    graph = [[0] * n for _ in range(n)]
    for x1, y1, x2, y2 in rectangle:
        x1, y1, x2, y2 = x1*2, y1*2, x2*2, y2*2
        for i in range(y1, y2+1):
            for j in range(x1, x2+1):
                graph[i][j] = 1
    for x1, y1, x2, y2 in rectangle:
        x1, y1, x2, y2 = x1*2, y1*2, x2*2, y2*2
        for i in range(y1+1, y2):
            for j in range(x1+1, x2):
                graph[i][j] = 0
    answer = 0
    characterX *= 2
    characterY *= 2
    itemX *= 2
    itemY *= 2
    q = deque([(characterX, characterY, 0)])


    while q:
        curr_x, curr_y, index = q.popleft()
        if itemX == curr_x and itemY == curr_y:
            return index//2
        for i in range(4):
            nx = curr_x + dx[i]
            ny = curr_y + dy[i]

            if graph[ny][nx] == 1 and -1 < nx < n and -1 < ny < n:
                graph[ny][nx] = 0
                q.append((nx, ny, index + 1))

    return answer







if __name__ == '__main__':
    solve()