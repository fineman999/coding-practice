"""
[PG] 42884 - 단속카메라
https://programmers.co.kr/learn/courses/30/lessons/42884
난이도: lv3
태그: greedy

시간복잡도: O(N Log N)
공간복잡도: O(N)
"""


def solution(routes: list[list[int]]) -> int:
    routes.sort(key= lambda x: x[1])
    cur_car_in, cur_car_out = routes[0][0], routes[0][1]
    answer = 1
    for i in range(1, len(routes)):
        car_in, car_out = routes[i][0], routes[i][1]
        if cur_car_out >= car_in:
            continue

        answer += 1
        cur_car_in, cur_car_out = car_in, car_out

    return answer
