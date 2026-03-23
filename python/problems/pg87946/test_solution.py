import pytest
from .solution import solution


import pytest
from .solution import solution


class TestSolution:
    """[PG] 87946 - 피로도"""

    def test_basic(self):
        """기본 케이스: 입출력 예제"""
        assert solution(80, [[80, 20], [50, 40], [30, 10]]) == 3

    def test_no_dungeons_possible(self):
        """탐험 가능한 던전이 하나도 없는 경우"""
        assert solution(10, [[80, 20], [50, 40]]) == 0

    def test_all_dungeons_at_once(self):
        """피로도가 충분하여 모든 던전을 순서 상관없이 탐험 가능한 경우"""
        assert solution(100, [[10, 5], [10, 5], [10, 5]]) == 3

    def test_order_matters(self):
        """순서에 따라 탐험 가능한 최대 수가 달라지는 경우"""
        # [40, 40]을 먼저 돌면 피로도가 0이 되어 더 못 돌지만,
        # [20, 10]들을 먼저 돌면 더 많이 돌 수 있음
        assert solution(40, [[40, 40], [20, 10], [20, 10]]) == 2

    def test_edge_single_dungeon(self):
        """던전이 하나만 있는 경우"""
        assert solution(50, [[50, 50]]) == 1
        assert solution(40, [[50, 50]]) == 0

    def test_large_input(self, benchmark):
        """최대 입력 케이스 (던전 8개) - 성능 측정 포함"""
        k = 5000
        # 모든 던전이 소모 피로도 1인 최악의 탐색 상황 가정
        dungeons = [[100, 1] for _ in range(8)]

        result = benchmark(lambda: solution(k, dungeons))
        assert result == 8

