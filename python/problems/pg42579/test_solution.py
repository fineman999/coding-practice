import pytest
from .solution import solution

class TestSolution:
    """[PG] 42579 - 베스트앨범"""

    def test_basic(self):
        """기본 케이스: 입출력 예시 검증"""
        genres = ["classic", "pop", "classic", "classic", "pop"]
        plays = [500, 600, 150, 800, 2500]
        # pop(3100) > classic(1450)
        # pop: 4(2500), 1(600)
        # classic: 3(800), 0(500)
        assert solution(genres, plays) == [4, 1, 3, 0]

    def test_single_song_in_genre(self):
        """장르에 곡이 하나만 있는 경우"""
        genres = ["classic", "pop", "classic", "jazz"]
        plays = [500, 600, 150, 3000]
        # jazz(3000) > pop(600) > classic(650) 이나 합계 기준이므로:
        # jazz(3000) > classic(650) > pop(600)
        # jazz: 3 | classic: 0, 2 | pop: 1
        assert solution(genres, plays) == [3, 0, 2, 1]

    def test_same_play_count(self):
        """장르 내 재생 횟수가 같을 때 고유 번호가 낮은 순서 검증"""
        genres = ["pop", "pop", "pop"]
        plays = [500, 500, 500]
        # 재생 횟수 같으면 번호 낮은 0, 1번만 선택
        assert solution(genres, plays) == [0, 1]

    def test_edge(self):
        """엣지 케이스: 최소 입력"""
        genres = ["classic"]
        plays = [100]
        assert solution(genres, plays) == [0]

    def test_large_input(self, benchmark):
        """큰 입력 성능 테스트 (10,000개)"""
        genres = ["genre" + str(i % 100) for i in range(10000)]
        plays = [i for i in range(10000)]

        result = benchmark(solution, genres, plays)
        assert len(result) <= 200  # 장르 100개 * 최대 2곡
