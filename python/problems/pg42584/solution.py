"""
[PG] 42584 - 주식가격
https://programmers.co.kr/learn/courses/30/lessons/42584
난이도: lv2
태그: stack,queue

시간복잡도: O(N): for문 N, stack -> push, pop N, 2N
공간복잡도: O(N)
"""


def solution(prices: list[int]) -> list[int]:
    stack = []
    n = len(prices)
    answer = [0]*n
    for i, price in enumerate(prices):
        while stack and prices[stack[-1]] > price:
            stack_pop = stack.pop()
            answer[stack_pop] = i - stack_pop
        stack.append(i)
    while stack:
        stack_pop = stack.pop()
        answer[stack_pop] = n-1 - stack_pop
    return answer
