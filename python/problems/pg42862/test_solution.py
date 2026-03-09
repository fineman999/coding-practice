import pytest
from .solution import solution


class TestSolution:
    """[PG] 42862 - 체육복"""

    @pytest.mark.parametrize(
        "n, lost, reserve, expected",
        [
            (
                5,
                [2, 4],
                [1, 3, 5],
                5
            ),
            (
                5,
                [2, 4],
                [3],
                4
            ),
            (
                3,
                [3],
                [1],
                2
            )
        ]
    )
    def test_basic(self, n, lost, reserve, expected):
        """기본 케이스"""
        assert solution(n, lost, reserve) == expected


    def test_large_input(self, benchmark):
        """큰 입력"""

        n = 30
        lost = [x for x in range(n) if x%2==0]
        reserve = [x for x in range(n) if x%2==1]
        expected = 30

        result = benchmark(solution, n, lost, reserve)

        assert result == expected
