"""
[PG] 49189 - 가장 먼 노드
https://programmers.co.kr/learn/courses/30/lessons/49189
난이도: lv3
태그: graph

시간복잡도: O()
공간복잡도: O(?)
"""

import sys
from collections import deque

input_data = sys.stdin.readline


def solution(n: int, vertex: list[list[int]]):
    graph = [[] for _ in range(n+1)]
    for start, end in vertex:
        graph[start].append(end)
        graph[end].append(start)

    visited = [0]*(n+1)
    # 초기화
    q = deque([1])
    visited[1] = 1

    # bfs 실행
    while q:
        curr_node = q.popleft()

        for next_node in graph[curr_node]:
            if visited[next_node] == 0:
                visited[next_node] = visited[curr_node] + 1
                q.append(next_node)

    max_step = max(visited)

    answer = 0
    for node_step in visited:
        if node_step == max_step:
            answer += 1

    return answer






def solve():
    n = int(input_data().strip())
    edge = int(input_data().strip())
    vertex = [list(map(int,input_data().split())) for _ in range(edge)]

    print(solution(n, vertex))


if __name__ == "__main__":
    solve()