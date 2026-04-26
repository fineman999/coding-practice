import pytest
from .solution import solution

class TestSolution:
    """[PG] 42884 - 단속카메라"""

    def test_basic(self):
        """기본 제공 입출력 예시"""
        routes = [[-20, -15], [-14, -5], [-18, -13], [-5, -3]]
        assert solution(routes) == 2

    def test_overlap_points(self):
        """진출입 지점이 겹치는 경우 (경계값 포함)"""
        # 모든 차량이 0 지점에서 만남
        assert solution([[-10, 0], [0, 10], [-5, 5]]) == 1
        # 선분들이 하나로 이어지는 경우
        assert solution([[1, 2], [2, 3], [3, 4]]) == 2

    def test_edge(self):
        """엣지 케이스: 차량이 1대인 경우 또는 모두 떨어진 경우"""
        # 차량 1대
        assert solution([[-20, -15]]) == 1
        # 모든 차량 경로가 겹치지 않음
        assert solution([[1, 2], [3, 4], [5, 6]]) == 3

    def test_same_routes(self):
        """동일한 경로를 가진 차량들"""
        assert solution([[10, 20], [10, 20], [10, 20]]) == 1

    def test_contained_routes(self):
        """한 경로가 다른 경로를 완전히 포함하는 경우"""
        assert solution([[10, 100], [20, 30], [25, 28]]) == 1
