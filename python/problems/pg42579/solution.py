"""
[PG] 42579 - 베스트앨범
https://programmers.co.kr/learn/courses/30/lessons/42579
난이도: lv2
태그: hash

시간복잡도: O(N Log N)
공간복잡도: O(N)
"""

from collections import defaultdict
def solution(genres: list[str], plays: list[int]) -> list[int]:
    genres_map_sum = defaultdict(int)
    genres_map = defaultdict(list)

    # 1. 데이터 구조화
    for i, (genre, play) in enumerate(zip(genres, plays)):
        genres_map_sum[genre] += play
        genres_map[genre].append((play, i))

    # 2. 총 재생 횟수 기준 내림차순으로 장르 정렬
    sorted_genres = sorted(genres_map_sum.items(), key=lambda x: x[1], reverse=True)

    # 3. 각 장르 내에서 조건에 맞춰 2곡 선택
    answer = []
    for element in sorted_genres:
        # 재생 횟수는 내림차순, 고유번호는 오름차순
        genre, _ = element
        get_genres = sorted(genres_map[genre], key=lambda x: (-x[0], x[1]))
        # 슬라이싱 후 인덱스(i)만 추출하여 추가
        answer.extend([i for _, i in get_genres[:2]])

    return answer
