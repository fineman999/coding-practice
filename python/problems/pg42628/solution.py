"""
[PG] 42628 - 이중우선순위큐
https://programmers.co.kr/learn/courses/30/lessons/42628
난이도: lv3
태그: heap

시간복잡도: O(NLogN)
공간복잡도: O(N)
"""

import heapq


def solution(operations: list[str]) -> list[int]:
    min_heap = []
    max_heap = []
    delete_heap_set = set()
    index = 0

    # 힙을 정리하는 헬퍼 함수
    def sync_heap(heap):
        while heap and heap[0][1] in delete_heap_set:
            heapq.heappop(heap)

    for operation in operations:
        command, number_str = operation.split()
        number = int(number_str)
        if command == "I":
            heapq.heappush(min_heap, (number, index))
            heapq.heappush(max_heap, (-number, index))
            index += 1
        else:
            target_heap = max_heap if number == 1 else min_heap
            sync_heap(target_heap)
            if target_heap:
                _, idx = heapq.heappop(target_heap)
                delete_heap_set.add(idx)

    sync_heap(min_heap)
    sync_heap(max_heap)
    return [-max_heap[0][0], min_heap[0][0]] if max_heap else [0, 0]
