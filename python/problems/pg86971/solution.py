"""
[PG] 86971 - 전력망을 둘로 나누기
https://programmers.co.kr/learn/courses/30/lessons/86971
난이도: lv2
태그: search

시간복잡도: O(n^2)
공간복잡도: O(n)
"""
from collections import deque, defaultdict
from typing import Dict, List


def solution(n: int, wires: list[list[int]]):

    # 1. 인접 리스트 생성
    graph = [[] for _ in range(n + 1)]
    for v1, v2 in wires:
        graph[v1].append(v2)
        graph[v2].append(v1)

    def get_network_size(start_node, ignore_node):
        visited = [False] * (n + 1)
        visited[start_node] = True
        queue = deque([start_node])
        count = 1
        while queue:
            curr = queue.popleft()
            for neighbor in graph[curr]:
                if not visited[neighbor] and neighbor != ignore_node:
                    visited[neighbor] = True
                    queue.append(neighbor)
                    count += 1
        return count

    answer = n
    for v1, v2 in wires:
        count1 = get_network_size(v1, v2)
        count2 = n - count1
        answer = min(answer, abs(count2 - count1))

    return answer


def solution01(n: int, wires: list[list[int]]) -> int:
    graph = defaultdict(list)
    for wire in wires:
        graph[wire[0]].append(wire[1])
        graph[wire[1]].append(wire[0])
    answer = n + 1
    for wire in wires:
        graph[wire[0]].remove(wire[1])
        graph[wire[1]].remove(wire[0])
        start = -1
        for key, value in graph.items():
            if len(value) > 0:
                start = key
                break
        count = bfs(graph, start)
        answer = min(abs(n - count - count), answer)
        graph[wire[0]].append(wire[1])
        graph[wire[1]].append(wire[0])
    return answer


def bfs(graph: Dict[int, List[int]], start: int):
    visited = set()
    queue = deque([start])
    visited.add(start)

    while queue:
        node = queue.popleft()
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
    return len(visited)

    return answer
