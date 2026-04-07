"""
[PG] 42627 - 디스크 컨트롤러
https://programmers.co.kr/learn/courses/30/lessons/42627
난이도: lv3
태그: heap

시간복잡도: O(nLogn)
공간복잡도: O(n)
"""

import heapq

import heapq

def solution(jobs: list[list[int]]):
    # 1. 원본 인덱스를 포함하여 데이터 재구성 [요청시간, 소요시간, 원본인덱스]
    # enumerate를 사용하여 정렬 전의 인덱스 i를 보존합니다.
    jobs_with_idx = [[job[0], job[1], i] for i, job in enumerate(jobs)]

    # 2. 요청 시각 순으로 정렬
    jobs_with_idx.sort()

    n = len(jobs)
    count, totalAmount, jIdx = 0, 0, 0
    time = 0
    heap = []

    while count < n:
        # 현재 시간까지 들어온 작업을 힙에 추가
        while jIdx < n and jobs_with_idx[jIdx][0] <= time:
            # 힙 우선순위: 소요시간(1) -> 요청시간(0) -> 원본인덱스(2)
            job = jobs_with_idx[jIdx]
            heapq.heappush(heap, (job[1], job[0], job[2]))
            jIdx += 1

        if heap:
            # 가장 우선순위 높은 작업 수행
            run_time, start_time, _ = heapq.heappop(heap)
            time += run_time
            totalAmount += (time - start_time)
            count += 1
        else:
            # 힙이 비어있으면 다음 작업 시점으로 점프
            time = jobs_with_idx[jIdx][0]

    return totalAmount // n
