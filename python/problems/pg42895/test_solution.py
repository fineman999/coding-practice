import pytest
from .solution import solution

class TestSolution:
    """[PG] 42895 - N으로 표현"""

    def test_basic_case1(self):
        """기본 케이스 1: N=5, number=12 -> 4"""
        assert solution(5, 12) == 4

    def test_basic_case2(self):
        """기본 케이스 2: N=2, number=11 -> 3"""
        assert solution(2, 11) == 3

    def test_edge_same(self):
        """엣지 케이스: N과 number가 같은 경우 -> 1"""
        assert solution(5, 5) == 1

    def test_edge_not_found(self):
        """엣지 케이스: 8번 안에 만들 수 없는 경우 -> -1"""
        # 아주 큰 수나 조합이 불가능한 경우
        assert solution(5, 31168) == -1

    def test_large_input(self):
        """큰 입력 및 성능: 최댓값 근처 연산"""
        # N이 1일 때 큰 수를 만드는 것이 연산량이 많음
        result = solution(1, 1121)
        assert isinstance(result, int)

    @pytest.mark.parametrize("n, number, expected", [
        (5, 12, 4),
        (2, 11, 3),
        (5, 5, 1),
        (5, 31168, -1),
    ])
    def test_all_cases(self, n, number, expected):
        """다양한 케이스 일괄 테스트"""
        assert solution(n, number) == expected
