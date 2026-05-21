"""
[PG] 43238 - 입국심사
https://programmers.co.kr/learn/courses/30/lessons/43238
난이도: lv3
태그: search

시간복잡도: O(?)
공간복잡도: O(?)
"""
import sys
input_data = sys.stdin.readline


#  이분 탐색
def solution(n: int, times: list[int]):
    left = 1
    right = max(times)*n
    answer = right
    while left <= right:
        mid = (left + right) // 2
        count = sum(mid // time for time in times)
        if count >= n:
            answer = mid
            right = mid - 1
        else:
            left = mid + 1
    return answer





def solve():
    n = int(input_data().strip())
    times = list(map(int, input_data().split()))
    print(solution(n, times))



if __name__ == "__main__":
    solve()