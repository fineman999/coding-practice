import pytest
from .solution import solution

class TestSolution:
    """[PG] 86971 - 전력망을 둘로 나누기"""

    def test_basic(self):
        """기본 케이스: 입출력 예시 검증"""
        # 예시 1
        assert solution(9, [[1, 3], [2, 3], [3, 4], [4, 5], [4, 6], [4, 7], [7, 8], [7, 9]]) == 3
        # 예시 2
        assert solution(4, [[1, 2], [2, 3], [3, 4]]) == 0
        # 예시 3
        assert solution(7, [[1, 2], [2, 7], [3, 7], [3, 4], [4, 5], [6, 7]]) == 1

    def test_edge(self):
        """엣지 케이스: 최소 입력 및 선형 구조"""
        # n의 최솟값 (2)
        assert solution(2, [[1, 2]]) == 0

        # 송전탑이 일렬로 연결된 경우 (n=3)
        assert solution(3, [[1, 2], [2, 3]]) == 1

        # 한 노드에 모든 노드가 붙어있는 경우 (Star Graph)
        assert solution(5, [[1, 2], [1, 3], [1, 4], [1, 5]]) == 3 # 1개 vs 4개 차이

    def test_large_input(self, benchmark):
        """큰 입력: 최대 n=100인 경우 성능 측정"""
        # n=100, 일렬로 길게 연결된 트리 생성
        n = 100
        wires = [[i, i + 1] for i in range(1, n)]

        # 벤치마크 실행 (결과값은 100이 짝수이므로 딱 반으로 나뉘어 0이 나와야 함)
        result = benchmark(solution, n, wires)
        assert result == 0
