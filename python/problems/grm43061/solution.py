"""
[GRM] 43061 - 계수기-만들기
https://level.goorm.io/exam/43061/%EA%B3%84%EC%88%98%EA%B8%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0/quiz/1
난이도: lv2
태그: implement

시간복잡도: O(?)
공간복잡도: O(?)
"""

import sys

input_data = sys.stdin.readline


def solution(n, max_numberings, start_numberings, k):

    for i in range(k):
        for j in range(len(max_numberings)-1, -1, -1):
            if max_numberings[j] > start_numberings[j]:
                start_numberings[j] += 1
                break
            else:
                start_numberings[j] = 0

    return "".join(map(str,start_numberings))




def solve():
    n = int(input_data().strip())
    max_numberings = list(map(int, input_data().split()))
    start_numberings = list(map(int, input_data().split()))
    k = int(input_data().strip())

    print(solution(n, max_numberings, start_numberings, k))



if __name__ == "__main__":
    solve()