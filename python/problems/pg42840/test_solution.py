import pytest
from .solution import solution


class TestSolution:
    """[PG] 42840 - 모의고사"""

    @pytest.mark.parametrize(
        "answers, expected",
        [
            (
                [1,2,3,4,5],
                [1]
            ),
            (
                [1,3,2,4,2],
                [1,2,3]
            )
        ]
    )
    def test_basic(self, answers, expected):
        """기본 케이스"""
        assert solution(answers) == expected

    # pytest problems/pg42840/test_solution.py
    def test_large_input_benchmark(self, benchmark):
        """큰 입력"""

        answer = [1,2,3,4,5]*(int(10_000/5))
        result = benchmark(solution, answer)

        expected = [1]
        assert result == expected
