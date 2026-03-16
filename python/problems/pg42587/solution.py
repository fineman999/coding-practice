"""
[PG] 42587 - 프로세스
https://programmers.co.kr/learn/courses/30/lessons/42587
난이도: lv2
태그: stack,queue

시간복잡도: O(n^2)
공간복잡도: O(n)
"""
from collections import deque

def solution(priorities: list[int], location: int):
    queue = deque(enumerate(priorities))
    priorities.sort(reverse=True)
    count = 0
    while queue:
        idx, p = queue.popleft()

        if p < priorities[count]:
            queue.append((idx, p))
        else:
            count+=1
            if idx == location:
                return count
    return count
