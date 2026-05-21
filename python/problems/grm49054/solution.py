"""
[GRM] 49054 - 어려운-문제
https://level.goorm.io/exam/49054/%EC%96%B4%EB%A0%A4%EC%9A%B4-%EB%AC%B8%EC%A0%9C/quiz/1
난이도: lv2
태그: implement

시간복잡도: O(?)
공간복잡도: O(?)
"""

import sys

input_data = sys.stdin.readline


def digit_sum_once(num: int) -> int:
    return sum(int(ch) for ch in str(num))


def factorial(n: int) -> int:
    result = 1
    for i in range(2, n + 1):
        result *= i
    return result


def solution(n: int) -> int:
    if n >= 6:
        return 9

    value = factorial(n)

    while value >= 10:
        value = digit_sum_once(value)

    return value

def solve():
    # 1. N 입력
    n = int(input_data().strip())

    # 2. 로직 실행
    print(solution(n))


if __name__ == "__main__":
    solve()