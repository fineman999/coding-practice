import pytest
from .solution import solution


import pytest
from .solution import solution

class TestSolution:
    """[PG] 42583 - 다리를 지나는 트럭"""

    @pytest.mark.parametrize(
        "bridge_length, weight, truck_weights, expected",
        [
            (2, 10, [7, 4, 5, 6], 8),                             # 문제 예시 1
            (100, 100, [10], 101),                                # 문제 예시 2
            (100, 100, [10, 10, 10, 10, 10, 10, 10, 10, 10, 10], 110), # 문제 예시 3
        ]
    )
    def test_basic(self, bridge_length, weight, truck_weights, expected):
        """기본 예제 케이스"""
        assert solution(bridge_length, weight, truck_weights) == expected

    def test_edge(self):
        """엣지 케이스: 최소 입력 및 무게 제한에 딱 걸리는 경우"""
        # 트럭 1대, 다리 길이 1
        assert solution(1, 10, [5]) == 2
        # 다리 무게 제한과 트럭 무게가 동일할 때
        assert solution(5, 10, [10, 10]) == 11

    def test_large_input(self, benchmark):
        """대량 입력 및 성능 테스트 (벤치마크)"""
        # 제한 사항 최대치 근접: 트럭 10,000대, 다리 길이 10,000
        bridge_length = 10000
        weight = 10000
        truck_weights = [1] * 10000

        # 벤치마크 측정 및 결과 검증
        # 10,000대가 길이 10,000인 다리를 1씩 무게로 건너면 10,000 + 10,000초 소요
        result = benchmark(solution, bridge_length, weight, truck_weights)
        assert result == 20000
