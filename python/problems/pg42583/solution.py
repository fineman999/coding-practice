"""
[PG] 42583 - 다리를 지나는 트럭
https://programmers.co.kr/learn/courses/30/lessons/42583
난이도: lv2
태그: stack,queue

시간복잡도: O(n + L)(n: 트럭 수, L: 다리 길이)
공간복잡도: O(n)
"""

from collections import deque

def solution(bridge_length: int, weight: int, truck_weights: list[int]) -> int:
    waiting_trucks = deque(truck_weights)
    total_weight, time = 0, 0
    length = [0] * bridge_length
    bridge = deque(length)
    while waiting_trucks or bridge:
        truck_out = bridge.popleft()
        total_weight -= truck_out
        if waiting_trucks:
            if total_weight + waiting_trucks[0] <= weight:
                truck_in = waiting_trucks.popleft()
                bridge.append(truck_in)
                total_weight += truck_in
            else:
                bridge.append(0)
        time += 1
    return time
