"""
[PG] 42746 - 가장 큰 수
https://programmers.co.kr/learn/courses/30/lessons/42746
난이도: lv2
태그: sort

시간복잡도: O(nLonN)
공간복잡도: O(N)
"""
from functools import cmp_to_key


def solution(numbers: list[int]) -> str:
    # 숫자들을 문자열로 바꿔서 정렬 기준으로 x*3을 사용한 내림차순 정렬
    numbers_str = list(map(str, numbers))
    numbers_str.sort(key=lambda x: x * 3, reverse=True)

    # 결과가 "0..." 으로 시작하면 모든 숫자가 0이므로 "0"을 반환
    if numbers_str and numbers_str[0] == "0":
        return "0"

    return "".join(numbers_str)


def solution02(numbers: list[int]) -> str:

    numbers_str = list(map(str, numbers))
    numbers_str.sort(key=cmp_to_key(compare))
    # 결과가 "0..." 으로 시작하면 모든 숫자가 0이므로 "0"을 반환
    if numbers_str and numbers_str[0] == "0":
        return "0"

    return "".join(numbers_str)


def compare(a: str, b: str) -> int:
    if a + b > b + a:
        return -1
    elif a + b < b + a:
        return 1
    else:
        return 0