"""
[PG] 42895 - N으로 표현
https://programmers.co.kr/learn/courses/30/lessons/42895
난이도: lv3
태그: DP

시간복잡도: O(4^K) - k는 최대 8 (문제에서 8회까지만 N을 사용할 수 있기 때문, 4는 사칙연산)
공간복잡도: O(U) (U는 8단계까지 생성된 중복 없는 결과값의 총 개수)
"""


def solution(N: int, number: int):
    max_number = 9
    dp = [set() for _ in range(max_number)]
    temp = N
    for i in range(1, max_number):
        e = dp[i]
        e.add(temp)
        temp = 10*temp + N

    for i in range(1, max_number):
        for j in range(1, i):
            for x in dp[j]:
                for y in dp[i - j]:
                    dp[i].add(x + y)
                    dp[i].add(x - y)
                    dp[i].add(x * y)
                    if y != 0:
                        dp[i].add(x // y)

        if number in dp[i]:
            return i

    return -1