"""
[GRM] 49060 - 개미-집합의-지름
https://level.goorm.io/exam/49060/%EA%B0%9C%EB%AF%B8-%EC%A7%91%ED%95%A9%EC%9D%98-%EC%A7%80%EB%A6%84/quiz/1
난이도: lv2
태그: structure

시간복잡도: O(n log n)
공간복잡도: O(1)
"""

import sys
input_data = sys.stdin.readline

# n: 개미수, d: 최대 길이는 d 이하로
def solution(n, d, ants):
    ants.sort()
    # 최대 값은 다 제거되는 경우
    answer = len(ants)
    if len(ants) < 2:
        return 0

    # left와 right에 각각 0번 인덱스 , 1번 인덱스 넣기
    right = 1
    for left in range(len(ants)):
        while right < n:
            if ants[right]-ants[left] <= d:
                answer = min(answer, n - right + left-1)
            else:
                break
            right += 1

    return answer

def solve():

    n, d = map(int, input_data().split())
    ants = list(map(int, input_data().split()))

    print(solution(n, d, ants))


if __name__ == "__main__":
    solve()