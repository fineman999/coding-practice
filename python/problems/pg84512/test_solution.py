import pytest
from .solution import solution

class TestSolution:
    """[PG] 84512 - 모음사전"""

    def test_basic(self):
        """프로그래머스 기본 입출력 예시"""
        assert solution("AAAAE") == 6
        assert solution("AAAE") == 10
        assert solution("I") == 1563
        assert solution("EIO") == 1189

    def test_edge(self):
        """첫 번째 단어와 마지막 단어 검증 (경계값 케이스)"""
        assert solution("A") == 1
        assert solution("UUUUU") == 3905

    def test_vowel_changes(self):
        """각 모음이 바뀔 때의 순서 변화 검증"""
        # A 다음의 주요 시작점 확인
        assert solution("E") == 782
        assert solution("I") == 1563
        assert solution("O") == 2344
        assert solution("U") == 3125

    @pytest.mark.benchmark
    def test_large_input(self, benchmark):
        """성능 측정 (최악의 케이스인 UUUUU 기준)"""
        result = benchmark(solution, "UUUUU")
        assert result == 3905
