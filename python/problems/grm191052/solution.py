"""
[GRM] 191052 - 블록-게임
https://level.goorm.io/exam/191052/%EB%B8%94%EB%A1%9D-%EA%B2%8C%EC%9E%84/quiz/1
난이도: lv2
태그: implement

시간복잡도: O(N)
공간복잡도: O(N)
"""
import sys
from collections import deque

input_data = sys.stdin.readline

directions = {
    'R': (1, 0),
    'L': (-1, 0),
    'U': (0, 1),
    'D': (0, -1)
}


def solution(n, d, s):
    stack = deque()
    distinct_positions = set()
    x, y = 0, 0
    answer = 1
    stack.append((x, y, answer))
    distinct_positions.add((x, y))
    for direction, score in zip(d, s):
        dx, dy = directions[direction]
        nx = dx + x
        ny = dy + y
        if (nx, ny) in distinct_positions:
            while stack:
                tx, ty, _ = stack[-1]
                _, _, temp_score = stack.pop()
                distinct_positions.remove((tx,ty))
                answer -= temp_score
                if nx == tx and ny == ty:
                    break
        distinct_positions.add((nx, ny))
        stack.append((nx, ny, score))
        answer += score
        x, y = nx, ny
    return answer


def solve():
    n = int(input_data().strip())
    d = input_data().strip()
    u = [i for i in d]
    s = list(map(int, input_data().split()))
    print(solution(n, u, s))


if __name__ == "__main__":
    solve()
