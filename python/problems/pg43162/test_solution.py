import pytest
from .solution import solution

class TestSolution:
    """[PG] 43162 - 네트워크"""

    def test_basic(self):
        """기본 케이스"""
        n = 3
        computers = [[1, 1, 0], [1, 1, 0], [0, 0, 1]]
        assert solution(n, computers) == 2

        n2 = 3
        computers2 = [[1, 1, 0], [1, 1, 1], [0, 1, 1]]
        assert solution(n2, computers2) == 1

    def test_edge(self):
        """엣지 케이스 - 모두 연결되지 않은 경우"""
        n = 4
        computers = [[1, 0, 0, 0], [0, 1, 0, 0], [0, 0, 1, 0], [0, 0, 0, 1]]
        assert solution(n, computers) == 4

    def test_large_input(self):
        """큰 입력 - 200개 노드가 하나의 선으로 연결된 경우"""
        n = 200
        # 모든 컴퓨터가 i-1, i, i+1 형태로 연결된 인접 행렬 생성
        computers = [[0] * n for _ in range(n)]
        for i in range(n):
            computers[i][i] = 1
            if i > 0:
                computers[i][i-1] = 1
            if i < n - 1:
                computers[i][i+1] = 1

        assert solution(n, computers) == 1
