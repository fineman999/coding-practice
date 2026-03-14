import pytest
from .solution import solution
import random
import string
import time

class TestSolution:
    """[PG] 42860 - 조이스틱"""

    @pytest.mark.parametrize(
        "name, expected",
        [
            ("JEROEN", 56),
            ("JAN", 23),
            ("A", 0),  # 전부 A, 이동도 변환도 필요없음
            ("B", 1),  # 한 글자, 변환만 1
            ("BA", 1),
            ("AB", 2),
        ]
    )
    def test_basic(self, name, expected):
        """기본 케이스"""
        assert solution(name) == expected

    @pytest.mark.parametrize(
        "name, expected",
        [
            ("A" * 20, 0),  # 최대길이, 전부 A (이동x, 변환x)
            ("B" + "A"*18 + "B", 3), # 양 끝만 B, 중간A, 점프되어야 함
        ]
    )
    def test_edge(self, name, expected):
        """엣지 케이스"""
        assert solution(name) == expected


@pytest.mark.benchmark(group="solution")
def test_benchmark_solution(benchmark):
    name = "JAN"
    benchmark(solution, name)

@pytest.mark.benchmark(group="solution-large")
def test_benchmark_large(benchmark):
    name = "J" + "A" * 18 + "Z"
    benchmark(solution, name)