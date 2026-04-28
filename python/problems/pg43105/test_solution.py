import pytest
import random
from .solution import solution

class TestSolution:
    """[PG] 43105 - 정수 삼각형"""

    def test_basic(self):
        """기본 케이스 - 프로그래머스 예제"""
        triangle = [
            [7],
            [3, 8],
            [8, 1, 0],
            [2, 7, 4, 4],
            [4, 5, 2, 6, 5]
        ]
        assert solution(triangle) == 30

    def test_edge(self):
        """엣지 케이스 - 높이가 1인 경우"""
        triangle = [[100]]
        assert solution(triangle) == 100

    def test_large_input(self):
        """큰 입력 - 높이 500"""
        height = 500
        # 모든 값이 1인 삼각형 (결과값은 500이어야 함)
        triangle = [[1] * (i + 1) for i in range(height)]
        assert solution(triangle) == 500

    def test_random_large_input(self):
        """큰 입력 - 랜덤 값 채우기 (성능 확인용)"""
        height = 500
        triangle = [[random.randint(0, 9999) for _ in range(i + 1)] for i in range(height)]

        result = solution(triangle)

        # 결과값은 최소 0 이상 (모든 숫자가 0 이상이므로)
        assert result >= 0
        assert isinstance(result, int)
