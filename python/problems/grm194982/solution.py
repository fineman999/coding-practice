"""
[GRM] 194982 - 장마
https://level.goorm.io/exam/194982/%EC%9E%A5%EB%A7%88/quiz/1
난이도: lv1
태그: hash

시간복잡도: O(?)
공간복잡도: O(?)
"""

import sys


input_data = sys.stdin.readline


def add_range(array: list[int], left:int, right:int, value:int):
    array[left] += value
    if right + 1 < len(array):
        array[right + 1] -= value

# 정렬 후 비가 오는 땅 선택하기
def merge_rains(positions):
    positions.sort()
    new_positions = [[positions[0][0], positions[0][1]]]
    for i in range(1, len(positions)):
        if new_positions[-1][1] >= positions[i][0]:
            if new_positions[-1][1] < positions[i][1]:
                new_positions[-1][1] = positions[i][1]
        else:
            new_positions.append([positions[i][0], positions[i][1]])
    return new_positions


def solution(n, m, k, positions):

    diff_areas = [0]*(n+1)
    diff_rains = [0]*(n+1)
    for start, end in positions:
        start -= 1
        end -= 1
        add_range(diff_areas, start, end, 1)

    # 비오는 날 카운트 하기 이때
    for i in range(0, m, 3):
        merge_positions = []
        for j in range(i, min(i+3, m)):
            start, end = positions[j]
            merge_positions.append([start, end])

        if len(merge_positions) < 3:
            continue
        update_merge_positions = merge_rains(merge_positions)
        for start, end in update_merge_positions:
            start -= 1
            end -= 1
            add_range(diff_rains, start, end, -1)

    answer = []
    area = 0
    rain = 0
    for i in range(n):
        area += diff_areas[i]
        rain += diff_rains[i]
        answer.append(k[i] + area + rain)

    return answer





def solve():
    n,m = map(int, input_data().split())
    k = list(map(int, input_data().split()))
    positions = [list(map(int, input_data().split())) for _ in range(m)]
    answer = solution(n, m, k, positions)

    print(*answer)


if __name__ == "__main__":
    solve()