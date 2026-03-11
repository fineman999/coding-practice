import pytest
from .solution import solution, solution02


class TestSolution:
    """[PG] 12909 - 올바른 괄호"""

    @pytest.mark.parametrize(
        "s, expected",
        [
            ("()()", True),
            ("(())()",	True),
            (")()(",False),
            ("(()(",False)
        ]
    )
    def test_basic(self, s, expected):
        """기본 케이스"""
        assert solution(s) == expected


    # pytest-benchmark를 사용한 성능 측정
    def test_benchmark_large_input(self, benchmark):
        """기존 스택 vs 카운트 방식의 성능 비교를 위한 벤치마크"""
        large_s = "(" * 50000 + ")" * 50000

        # benchmark 픽스처가 solution(large_s)의 실행 시간을 여러 번 측정하여 통계를 내줍니다.
        result = benchmark(solution, large_s)

        assert result is True

    # pytest-benchmark를 사용한 성능 측정
    def test_benchmark_large_input02(self, benchmark):
        """기존 스택 vs 카운트 방식의 성능 비교를 위한 벤치마크"""
        large_s = "(" * 50000 + ")" * 50000

        # benchmark 픽스처가 solution(large_s)의 실행 시간을 여러 번 측정하여 통계를 내줍니다.
        result = benchmark(solution02, large_s)

        assert result is True
