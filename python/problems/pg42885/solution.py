"""
[PG] 42885 - 구명보트
https://programmers.co.kr/learn/courses/30/lessons/42885
난이도: lv2
태그: greedy

시간복잡도: O(NlogN)
공간복잡도: O(N)
"""
from collections import deque

def solution(people: list[int], limit: int):
    answer = 0
    people.sort(reverse=True)
    q = deque(people)
    while q:

        person = q.popleft()
        if q and q[-1] + person <= limit:
            q.pop()

        answer+=1

    return answer
