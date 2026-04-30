"""
[PG] 43162 - 네트워크
https://programmers.co.kr/learn/courses/30/lessons/43162
난이도: lv3
태그: DFS/BFS

시간복잡도: O(N^2)
공간복잡도: O(N)
"""

from collections import deque


def solution(n: int, computers: list[list[int]]):
    answer = 0
    q = deque()
    visited = [False] * n
    for i in range(n):
        if visited[i]:
            continue
        q.append(i)
        visited[i] = True
        answer += 1
        while q:
            curr_node = q.popleft()
            for next_node, value in enumerate(computers[curr_node]):
                if not visited[next_node] and value == 1:
                    visited[next_node] = True
                    q.append(next_node)

    return answer
