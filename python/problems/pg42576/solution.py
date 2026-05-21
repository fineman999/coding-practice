"""
[PG] 42576 - 완주하지 못한 선수
https://programmers.co.kr/learn/courses/30/lessons/42576
난이도: lv1
태그: hash

시간복잡도: O(N)
공간복잡도: O(N)
"""
import sys

input_data = sys.stdin.readline

from collections import defaultdict


def solution(participant, completion):
    participant_map = defaultdict(int)

    for player in participant:
        participant_map[player] += 1

    for player in completion:
        participant_map[player] -= 1

    for player, cnt in participant_map.items():
        if cnt == 1:
            return player
    return ""

def solution02(participant, completion):
    participant_map = defaultdict()
    temp = 0
    for player in participant:
        participant_map[hash(player)] = player
        temp += hash(player)
    for player in completion:
        temp -= hash(player)

    return participant_map[temp]


def solve():
    n = int(input_data().strip())
    participant = [input_data().strip() for _ in range(n)]
    m = int(input_data().strip())
    completion = [input_data().strip() for _ in range(m)]

    print(solution(participant, completion))


if __name__ == "__main__":
    solve()
