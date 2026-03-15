import pytest
from .solution import solution

class TestSolution:
    """[PG] 43165 - 타겟 넘버"""

    @pytest.mark.parametrize(
        "numbers, target, expected",
        [
            # 1. 문제 예시 #1
            ([1, 1, 1, 1, 1], 3, 5),
            # 2. 문제 예시 #2
            ([4, 1, 2, 1], 4, 2),
        ]
    )
    def test_basic(self, numbers, target, expected):
        """여러 대표 케이스"""
        assert solution(numbers, target) == expected

    # ---------------------------------------------------------
    # 🚀 벤치마크 테스트 (큰 입력, Pytest benchmark fixture 활용)
    # ---------------------------------------------------------
    def test_large_input_benchmark(self, benchmark):
        """numbers 최대 크기(20) 벤치마크"""
        numbers = [1] * 20
        # (1 + -1 조합 2^20개 → 그 중 합이 0인 경우의 수)
        target = 0
        # 답은 comb(20, 10) = 184756 (이항계수)
        expected = 184756

        result = benchmark(solution, numbers, target)
        assert result == expected