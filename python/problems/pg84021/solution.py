"""
[PG] 84021 - 퍼즐 조각 채우기
https://programmers.co.kr/learn/courses/30/lessons/84021
난이도: lv3
태그: DFS/BFS

시간복잡도: O(N^2)
공간복잡도: O(N^2)
"""

import sys
from collections import deque

input_data = sys.stdin.readline

dx = [0,1,0,-1]
dy = [1,0,-1,0]
def solution(game_board: list[list[int]], table: list[list[int]]) -> int:
    n = len(game_board)

    game_shapes = get_shapes(game_board, n, 0)
    table_shapes = get_shapes(table, n, 1)

    answer = 0
    used = [False]*len(table_shapes)

    for blank in game_shapes:
        for i, piece in enumerate(table_shapes):
            if used[i]:
                continue
            if len(blank) != len(piece):
                continue
            # rotate 네번
            for _ in range(4):
                if piece == blank:
                    answer += len(piece)
                    used[i] = True
                    break
                piece = rotate(piece)

            if used[i]:
                break
    return answer





def normalize(shape: list[tuple[int, int]]):
    min_x = min(x for y, x in shape)
    min_y = min(y for y, x in shape)
    normalized_shape = []
    for y, x in shape:
        normalized_shape.append((y-min_y, x-min_x))
    return sorted(normalized_shape)

def rotate(shape: list[tuple[int, int]]):
    rotate_shape = []
    for y, x in shape:
        rotate_shape.append((x, -y))
    return normalize(rotate_shape)

def get_shapes(board: list[list[int]], n: int, target: int):
    shapes = []
    visited = [[False]*n for _ in range(n)]
    q = deque()
    for i in range(n):
        for j in range(n):
            if board[i][j] == target and not visited[i][j]:
                q.append((i, j))
                shape = [(i, j)]
                visited[i][j] = True
                while q:
                    y, x = q.popleft()
                    for k in range(4):
                        ny = dy[k] + y
                        nx = dx[k] + x
                        if -1 < nx < n and -1 < ny < n and board[ny][nx] == target and not visited[ny][nx]:
                            visited[ny][nx] = True
                            shape.append((ny, nx))
                            q.append((ny, nx))

                # 가공화하기
                shapes.append(normalize(shape))
    return shapes

def solve():
    n = int(input_data().strip())
    game_board = [list(map(int, input_data().split())) for _ in range(n)]
    table = [list(map(int, input_data().split())) for _ in range(n)]
    print(solution(game_board, table))




if __name__ == "__main__":
    solve()