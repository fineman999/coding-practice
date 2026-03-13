"""
[PG] 42839 - 소수 찾기
https://programmers.co.kr/learn/courses/30/lessons/42839
난이도: lv2
태그: search

시간복잡도: O(N^2)
공간복잡도: O(N)
"""
from itertools import permutations


def solution(numbers: str) -> int:
    return solution01(numbers)


def solution02(numbers: str) -> int:
    candidates = set()
    for i in range(1, len(numbers) + 1):
        for p in permutations(numbers, i):
            candidates.add(int(''.join(p)))

    print(candidates)
    return sum(1 for i in candidates if is_prime(i))


def is_prime(n: int) -> bool:
    if  n < 2:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True



def solution01(numbers: str) -> int:
    array = [i for i in numbers]
    numbers_set = set()

    recursive("", array, numbers_set)

    count = 0
    for e in numbers_set:
        is_valid = True
        for i in range(2, int(e**0.5)+1):
            if e % i == 0:
                is_valid = False
                break
        if is_valid:
            count += 1

    return count


def recursive(current_element: str, current_array: list[str],numbers_set: set):
    if current_element and int(current_element) > 1:
        numbers_set.add(int(current_element))

    for i, e in enumerate(current_array):
        recursive(e + current_element, current_array[:i] + current_array[i+1:], numbers_set)
