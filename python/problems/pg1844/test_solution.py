import pytest
import random
from .solution import solution

class TestSolution:
    """[PG] 1844 - 게임 맵 최단거리"""

    def test_basic_success(self):
        """기본 케이스: 도달 가능한 경우 (예제 1)"""
        maps = [
            [1, 0, 1, 1, 1],
            [1, 0, 1, 0, 1],
            [1, 0, 1, 1, 1],
            [1, 1, 1, 0, 1],
            [0, 0, 0, 0, 1]
        ]
        assert solution(maps) == 11

    def test_basic_failure(self):
        """기본 케이스: 도달 불가능한 경우 (예제 2)"""
        maps = [
            [1, 0, 1, 1, 1],
            [1, 0, 1, 0, 1],
            [1, 0, 1, 1, 1],
            [1, 1, 1, 0, 0],
            [0, 0, 0, 0, 1]
        ]
        assert solution(maps) == -1

    def test_edge_cases(self):
        """엣지 케이스: 최소 크기 및 직선 경로"""
        # 2x2 최소 맵
        assert solution([[1, 1], [1, 1]]) == 3
        # 한 줄로 된 맵
        assert solution([[1, 1, 1, 1]]) == 4
        # 벽으로 완전히 막힌 시작점
        assert solution([[1, 0], [0, 1]]) == -1

    def test_large_input_benchmark(self, benchmark):
        """큰 입력 성능 측정: 100x100 최악의 경우 (S자 경로)"""
        n, m = 100, 100
        large_map = [[0] * m for _ in range(n)]

        # S자 형태로 길 만들기
        for i in range(n):
            if i % 2 == 0:
                large_map[i] = [1] * m
            else:
                if (i // 2) % 2 == 0:
                    large_map[i][m-1] = 1
                else:
                    large_map[i][0] = 1

        # 마지막 칸 목적지 보장
        large_map[n-1][m-1] = 1

        result = benchmark(solution, large_map)
        assert result != -1

    def test_random_large_map(self, benchmark):
        """랜덤 100x100 맵 벤치마크"""
        n, m = 100, 100
        # 통과 가능 확률을 높이기 위해 1의 비중을 높게 설정
        random_map = [[1 if random.random() > 0.2 else 0 for _ in range(m)] for _ in range(n)]
        random_map[0][0] = 1
        random_map[n-1][m-1] = 1

        benchmark(solution, random_map)
