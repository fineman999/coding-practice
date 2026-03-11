"""
[PG] 12909 - 올바른 괄호
https://programmers.co.kr/learn/courses/30/lessons/12909
난이도: lv2
태그: stack,queue

시간복잡도: O(n)
공간복잡도: O(n)
"""
from collections import deque

# deque 기반
def solution(s: str) -> bool:
    stack = deque()

    for e in s:
        if not stack:
            stack.append(e)
        else:
            stack_pop = stack.pop()
            if not (stack_pop == "(" and e == ")"):
                stack.append(stack_pop)
                stack.append(e)

    if stack:
        return False

    return True


# 정수 카운터 기반
# 공간복잡도가 O(1)로 변경
def solution02(s: str) -> bool:
    count = 0

    for char in s:
        if char == "(":
            count += 1
        else:
            if count == 0:
                return False
            count -= 1

    return count == 0