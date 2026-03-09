"""
[PG] 42862 - 체육복
https://programmers.co.kr/learn/courses/30/lessons/42862
난이도: lv1
태그: greedy

시간복잡도: O(n)
공간복잡도: O(n)
"""


def solution(n: int, lost: list[int], reserve: list[int]) -> int:
    students = [1] * n
    real_lost = set(lost) -set(reserve)
    real_reserve = set(reserve) - set(lost)
    for s in real_lost:
        students[s - 1] = 0
    for s in real_reserve:
        students[s - 1] = 2

    for i in range(n):
        if students[i] == 0:
            if i - 1 >= 0 and students[i - 1] > 1:
                students[i - 1] -= 1
                students[i] += 1
            elif i + 1 < n and students[i + 1] > 1:
                students[i + 1] -= 1
                students[i] += 1
    return n - students.count(0)
