import pytest
import random
from .solution import solution

class TestSolution:
    """[PG] 42627 - 디스크 컨트롤러"""

    def test_basic(self):
        """기본 케이스: 예제 입력"""
        jobs = [[0, 3], [1, 9], [3, 5]]
        assert solution(jobs) == 8

    def test_same_request_time(self):
        """엣지 케이스: 모든 작업이 동시에 요청됨"""
        jobs = [[0, 10], [0, 3], [0, 5]]
        # (3 + 8 + 18) // 3 = 9
        assert solution(jobs) == 9

    def test_with_gap(self):
        """엣지 케이스: 작업 사이에 공백이 있는 경우"""
        jobs = [[0, 3], [10, 5], [11, 2]]
        # 0~3(3), 10~12(1), 12~17(7) -> (3+1+7)//3 = 3
        assert solution(jobs) == 4

    def test_large_input(self):
        """큰 입력: 최대 500개 작업 처리 및 성능 확인"""
        # 요청 시각 0~1000, 소요 시간 1~1000 랜덤 생성
        big_jobs = [[random.randint(0, 1000), random.randint(1, 1000)] for _ in range(500)]
        result = solution(big_jobs)
        assert isinstance(result, int)
        assert result >= 0

    def test_single_job(self):
        """엣지 케이스: 작업이 하나만 있는 경우"""
        jobs = [[5, 10]]
        assert solution(jobs) == 10  # 15 - 5 = 10
