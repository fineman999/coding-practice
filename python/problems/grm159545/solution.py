"""
[GRM] 159545 - 0커플
https://level.goorm.io/exam/159545/0%EC%BB%A4%ED%94%8C/quiz/1
난이도: lv2
태그: structure

시간복잡도: O(n)
공간복잡도: O(n)
"""

import sys

input_data = sys.stdin.readline
from collections import defaultdict

def solution(n, friends):
    conversation_map = defaultdict(int)

    for friend in friends:
        # 맵에다가 절대값은 키, 값에다가 더하기만일 기존에 있는 경우면 두개가 더해져서 0이됨
        if friend < 0:
            conversation_map[-friend] += friend
        else:
            conversation_map[friend] += friend

    answer = 0
    for key, value in conversation_map.items():
        if value != 0:
            answer += value
    return answer







def solve():
    # 1. 지인의 수
    n = int(input_data().strip())
    # 2. 지인
    friends = list(map(int, input_data().split()))
    print(solution(n, friends))



if __name__ == "__main__":
    solve()