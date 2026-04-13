"""
[PG] 42861 - 섬 연결하기
https://programmers.co.kr/learn/courses/30/lessons/42861
난이도: lv3
태그: greedy

시간복잡도: O(E Log V) V: 노드, E: 가중치
공간복잡도: O(V + E)
"""

import heapq


def solution(n: int, costs: list[list[int]]) -> int:
    answer = 0
    visited = [False] * n
    start_node = 0
    q = []
    heapq.heappush(q, (0, start_node))
    connected_count = 0
    graph = [[] for _ in range(n)]
    for cost in costs:
        graph[cost[0]].append((cost[1], cost[2]))
        graph[cost[1]].append((cost[0], cost[2]))

    while q:
        weight, now = heapq.heappop(q)
        if visited[now]:
            continue
        visited[now] = True
        answer += weight
        connected_count += 1

        if connected_count == n:
            break
        for next_node, next_weight in graph[now]:
            if not visited[next_node]:
                heapq.heappush(q, (next_weight, next_node))
    return answer
