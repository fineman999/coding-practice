"""
[PG] 42577 - 전화번호 목록
https://programmers.co.kr/learn/courses/30/lessons/42577
난이도: lv1
태그: hash

시간복잡도: O(lxnlogn)
공간복잡도: O(n)
"""


def solution(phone_book: list[str]) -> bool:
    phone_book.sort()
    return not any(p2.startswith(p1) for p1, p2 in zip(phone_book, phone_book[1:]))

