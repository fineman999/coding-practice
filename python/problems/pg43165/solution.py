"""
[PG] 43165 - 타겟 넘버
https://programmers.co.kr/learn/courses/30/lessons/43165
난이도: lv2
태그: dfs,bfs

시간복잡도: O(2^N)
공간복잡도: O(N)
"""
from functools import lru_cache


def solution(numbers, target):
    return solution02(numbers, target)


def solution02(numbers, target):
    n = len(numbers)

    @lru_cache(None)  # 실무에서는 클리어 무조건 필요, 인수로 값이 변동 가능하면 안됨
    def dfs_cache(index, current_sum):
        if index == n:
            return 1 if current_sum == target else 0

        # + 연산과 - 연산을 재귀로 수행
        return dfs_cache(index + 1, current_sum + numbers[index]) + \
            dfs_cache(index + 1, current_sum - numbers[index])

    return dfs_cache(0, 0)


def dfs(numbers: int, target: int, index: int, n: int, count: int):
    if index == n:
        return 1 if target == count else 0
    return dfs(numbers, target, index + 1, n, count + numbers[index]) + dfs(numbers, target, index + 1, n,
                                                                            count - numbers[index])
