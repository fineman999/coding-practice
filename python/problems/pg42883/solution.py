"""
[PG] 42883 - 큰 수 만들기
https://programmers.co.kr/learn/courses/30/lessons/42883
난이도: lv2
태그: greedy

시간복잡도: O(n) N
공간복잡도: O(n)
"""
from collections import  deque


def solution(number, k):
    stack = deque()

    cnt = 0
    for element in number:
        while stack and stack[-1] < element and cnt < k:
            stack.pop()
            cnt += 1

        stack.append(element)

    answer = "".join([str(i) for i in stack])
    if len(answer) > (len(number)-k):
        return answer[:len(number)-k]
    return answer








