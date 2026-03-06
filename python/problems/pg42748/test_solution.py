import pytest
from .solution import solve


class TestSolve:
    """[PG] 42748 - k번째수"""

    def test_basic(self):
        """기본 케이스"""
        assert solve() == 0

    def test_edge(self):
        """엣지 케이스"""
        pass

    def test_large_input(self):
        """큰 입력"""
        pass
