"""
[PG] 42577 - 전화번호 목록
https://programmers.co.kr/learn/courses/30/lessons/42577
난이도: lv1
태그: hash

시간복잡도: O(lxnlogn)
공간복잡도: O(n)
"""

import sys

input_data = sys.stdin.readline

def solution(phone_book: list[str]):
    # 길이 크기로 내림차순
    phone_book.sort(key= lambda n: len(n), reverse=True)

    phone_map = set()
    for phone in phone_book:
        phone_map.add(phone)

    for phone in phone_book:
        temp = ""
        for i in range(len(phone)-1):
            temp += phone[i]
            if temp in phone_map:
                return False
    return True





def solve():
    phone_book = list(map(int, input_data().split()))
    
    solution(phone_book)

if __name__=="__main__":
    solve()


