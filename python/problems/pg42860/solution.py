"""
[PG] 42860 - 조이스틱
https://programmers.co.kr/learn/courses/30/lessons/42860
난이도: lv2
태그: greedy

시간복잡도: O(n2)
공간복잡도: O(n)
"""


def solution(name: str) -> int:
    answer = find_min_count(name)


    return answer

def find_min_count(name: str):
    n = len(name)
    count = 0
    for i in range(n):
        count += min(ord(name[i]) - ord('A'), ord('Z') + 1 - ord(name[i]))

    min_move = n - 1  # 오른쪽만 이동할 때
    for i in range(n):
        next_idx = i + 1
        # 연속된 A 구간 건너뛰기
        while next_idx < n and name[next_idx] == 'A':
            next_idx += 1
        # 되돌아가기, 앞뒤점프 모두 시뮬
        min_move = min(min_move, 2*i + n - next_idx)
        # 반대쪽 방향도 비교
        min_move = min(min_move, i + 2*(n - next_idx))

    return count + min_move
