import pytest
from .solution import solution


class TestSolution:
    """[PG] 42839 - 소수 찾기"""

    @pytest.mark.parametrize(
        "numbers, expected",
        [
            (
                "17",
                3
            ),
            (
                "011",
                2
            )
        ]
    )
    def test_basic(self, numbers, expected):
        """기본 케이스"""
        assert solution(numbers) == expected

    @pytest.mark.benchmark(group="solution")
    @pytest.mark.parametrize(
        "numbers",
        [
            "1234567",
            "9876543",
            "11117",
        ]
    )
    def test_large_input(self, benchmark, numbers):
        """큰 입력 벤치마크"""
        result = benchmark(solution, numbers)
        # (옵션) 범위 내 있는지 확인 가능
        assert result >= 0
