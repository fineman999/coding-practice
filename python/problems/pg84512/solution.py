"""
[PG] 84512 - 모음사진
https://programmers.co.kr/learn/courses/30/lessons/84512
난이도: lv2
태그: search

시간복잡도: O(5^n) (n=5)
공간복잡도: O(n) (n=5)(스택 메모리사용량은 단어 길이에 비례)
"""


def solution(word):
    cnt, result = 0, 0

    def recursive_word(curr_word):
        nonlocal cnt, result
        if curr_word == word:
            result = cnt
            return True

        if len(curr_word) == 5:
            return False

        for e in alpha:
            cnt +=1
            if recursive_word(curr_word + e):
                return True
        return False

    recursive_word("")
    return result

alpha = ['A','E','I','O','U']
count = 0
answer = 0
def solution02(word):
    global answer
    global count
    answer, count = 0, 0
    n = 5
    recursive("", word, n)

    return answer

def recursive(curr_word: str, word: str, n: int):
    global count
    global alpha
    global answer
    if word == curr_word:
        answer = count
        return

    for e in alpha:
        if len(curr_word + e) < 6:
            count+=1
            recursive(curr_word+e, word, n)
