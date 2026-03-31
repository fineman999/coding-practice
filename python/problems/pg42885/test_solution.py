import random

import pytest
from .solution import solution


class TestSolution:
    """[PG] 42885 - 구명보트"""

    def test_basic(self):
        """기본 입출력 예시"""
        assert solution([70, 50, 80, 50], 100) == 3
        assert solution([70, 80, 50], 100) == 3

    def test_edge(self):
        """엣지 케이스"""
        # 1명만 있는 경우
        assert solution([40], 100) == 1
        # 모든 사람이 제한 무게의 절반을 넘는 경우 (함께 못 탐)
        assert solution([60, 60, 60], 100) == 3

    @pytest.mark.benchmark(group="solution_performance")
    def test_large_input_benchmark(self, benchmark):
        """대규모 입력 성능 측정 (50,000명)"""
        # 데이터 생성: 정렬되지 않은 50,000명의 몸무게
        limit = 240
        people = [random.randint(40, 240) for _ in range(50000)]

        # benchmark() 안에 측정하고 싶은 함수와 인자를 전달합니다.
        result = benchmark(solution, people, limit)

        # 결과값에 대한 최소한의 검증 (모든 사람이 구출되었으므로 보트 수는 1 이상)
        assert result >= 25000