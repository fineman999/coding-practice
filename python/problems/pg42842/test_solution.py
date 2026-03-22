import pytest
from .solution import solution

class TestSolution:
    """[PG] 42842 - 카펫 (완전탐색)"""

    @pytest.mark.parametrize("brown, yellow, expected", [
        (10, 2, [4, 3]),   # 입출력 예 1
        (8, 1, [3, 3]),    # 입출력 예 2
        (24, 24, [8, 6]),  # 입출력 예 3
    ])
    def test_basic(self, brown, yellow, expected):
        """기본 입출력 예시 케이스"""
        assert solution(brown, yellow) == expected

    def test_edge(self):
        """엣지 케이스: 최소 크기 및 가로가 긴 경우"""
        # 가장 작은 카펫
        assert solution(8, 1) == [3, 3]
        # yellow가 가로로 길게 배치된 경우 (yellow 3, brown 12)
        assert solution(12, 3) == [5, 3]

    def test_large_input(self, benchmark):
        """큰 입력 케이스 및 성능 측정"""
        # 제한사항 내의 큰 값 예시 (brown 4004, yellow 1,000,000)
        # 예상 결과: 가로 1002, 세로 1002
        result = benchmark(solution, 4004, 1000000)
        assert result == [1002, 1002]

    def test_validation(self):
        """반환 조건 검증: 가로 >= 세로"""
        brown, yellow = 18, 6
        result = solution(brown, yellow)

        width, height = result
        assert width >= height
        assert (width * height) == (brown + yellow)
