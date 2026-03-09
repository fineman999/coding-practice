import pytest
from .solution import solution


class TestSolution:
    """[PG] 42862 - 체육복"""

    def test_basic(self):
        """기본 케이스"""
        assert solution() == 0

    def test_edge(self):
        """엣지 케이스"""
        pass

    def test_large_input(self):
        """큰 입력"""
        pass
