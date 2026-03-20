import pytest
import random
from .solution import solution

class TestSolution:
    """[PG] 42747 - H-index"""

    def test_basic(self):
        """기본 케이스 및 예제"""
        assert solution([3, 0, 6, 1, 5]) == 3
        assert solution([10, 8, 5, 4, 3]) == 4
        assert solution([25, 8, 5, 3, 3]) == 3

    def test_edge(self):
        """엣지 케이스: 모두 0인 경우, 논문이 1개인 경우 등"""
        assert solution([0, 0, 0]) == 0
        assert solution([10, 10, 10]) == 3
        assert solution([1]) == 1
        assert solution([0]) == 0
        assert solution([9, 7, 6, 2, 1]) == 3

    def test_large_input(self, benchmark):
        """큰 입력 성능 테스트 (최대 1,000편)"""
        # 논문 1,000편, 인용 횟수 랜덤 (0~10,000)
        large_citations = [random.randint(0, 10000) for _ in range(1000)]

        # 벤치마크 측정 및 결과 존재 여부 확인
        result = benchmark(solution, large_citations)
        assert 0 <= result <= 1000
