import pytest
from .solution import solution

class TestSolution:
    """[PG] 42861 - 섬 연결하기"""

    def test_basic(self):
        """기본 케이스: 입출력 예시"""
        n = 4
        costs = [[0, 1, 1], [0, 2, 2], [1, 2, 5], [1, 3, 1], [2, 3, 8]]
        assert solution(n, costs) == 4

    def test_edge(self):
        """엣지 케이스: 섬이 1개이거나 최소 연결인 경우"""
        # 섬이 1개면 다리를 지을 필요가 없음
        assert solution(1, []) == 0
        # 섬이 2개고 다리가 하나인 경우
        assert solution(2, [[0, 1, 5]]) == 5

    def test_same_costs(self):
        """특이 케이스: 모든 비용이 같을 때"""
        n = 3
        costs = [[0, 1, 10], [1, 2, 10], [0, 2, 10]]
        # 어떤 두 다리를 선택해도 결과는 20
        assert solution(n, costs) == 20

    def test_large_input(self):
        """큰 입력: 최대 섬 개수(100개) 근사치"""
        n = 5
        # 일직선 연결 (0-1, 1-2, 2-3, 3-4) 비용이 가장 저렴한 경우
        costs = [
            [0, 1, 1], [1, 2, 1], [2, 3, 1], [3, 4, 1],
            [0, 4, 10], [0, 2, 5], [1, 3, 5]
        ]
        assert solution(n, costs) == 4

    def test_disconnected_path_priority(self):
        """그리디 알고리즘 검증: 비용이 낮은 다리부터 선택되는지 확인"""
        n = 4
        costs = [[0, 1, 1], [2, 3, 1], [1, 2, 1]]
        assert solution(n, costs) == 3
