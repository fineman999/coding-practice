"""
[PG] 42626 - 더 맵게
https://programmers.co.kr/learn/courses/30/lessons/42626
난이도: lv2
태그: heap

시간복잡도: O(N log N)
공간복잡도: O(N)
"""
import heapq

def solution(scoville: list[int], K: int) -> int:
    heapq.heapify(scoville)

    count = 0
    while len(scoville) >= 2 and scoville[0] < K:
        first = heapq.heappop(scoville)
        third = first + scoville[0]*2
        heapq.heapreplace(scoville, third)
        count += 1

    if K <= scoville[0]:
        return count
    return -1

