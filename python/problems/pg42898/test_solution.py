import pytest
from .solution import solution

class TestSolution:
    """[PG] 42898 - 등굣길"""

    def test_basic(self):
        """기본 케이스 (입출력 예시)"""
        # m=4, n=3, puddles=[[2, 2]] -> result=4
        assert solution(4, 3, [[2, 2]]) == 4

    def test_edge(self):
        """엣지 케이스: 장애물로 인해 경로가 막히거나 최소 크기인 경우"""
        # 1. 장애물이 모든 경로를 막은 경우
        assert solution(3, 3, [[1, 2], [2, 1]]) == 0

        # 2. 장애물이 없는 경우 (m=2, n=2)
        assert solution(2, 2, []) == 2

        # 3. 직선 경로 (한 줄인 경우)
        assert solution(1, 4, []) == 1
        assert solution(4, 1, []) == 1

        # 4. 직선 경로 중 물에 잠긴 곳이 있는 경우
        assert solution(1, 4, [[1, 2]]) == 0

    def test_large_input(self):
        """큰 입력: 효율성 및 나머지 연산 확인"""
        # 최대 크기 100x100, 장애물 없음
        # 이 결과값은 직접 계산하기보다 로직의 시간 복잡도와 나머지 연산 처리를 확인하는 용도입니다.
        result = solution(100, 100, [])
        assert isinstance(result, int)
        assert 0 <= result < 1_000_000_007
