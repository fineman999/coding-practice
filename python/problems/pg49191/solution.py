"""
[PG] 49191 - 순위
https://programmers.co.kr/learn/courses/30/lessons/49191
난이도: lv3
태그: graph

시간복잡도: O(?)
공간복잡도: O(?)
"""


import sys


from collections import deque

input_data = sys.stdin.readline


def solution(n, results):
    win_graph = [[] for _ in range(n+1)]
    lose_graph = [[] for _ in range(n+1)]

    for win_player, lose_player in results:
        win_graph[win_player].append(lose_player)
        lose_graph[lose_player].append(win_player)

    answer = 0
    for i in range(1, n+1):
        win_count = bfs(n, i, win_graph)
        lose_count = bfs(n, i, lose_graph)
        if win_count + lose_count == n - 1:
            answer += 1
    return answer


def bfs(n, start: int, graph):
    q = deque([start])
    visited = [False] * (n + 1)
    visited[start] = True
    while q:
        player = q.popleft()

        for next_player in graph[player]:
            if not visited[next_player]:
                q.append(next_player)
                visited[next_player] = True
    count = 0
    for player in visited:
        if player:
            count +=1
    return count - 1


def solve():
    n = int(input_data().strip())
    edges = int(input_data().strip())
    results = [list(map(int, input_data().split())) for _ in range(edges)]

    solution(n, results)


if __name__ == "__main__":
    solve()