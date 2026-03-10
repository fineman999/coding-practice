import pytest
from .solution import solution

class TestSolution:
    """[PG] 42578 - 의상"""

    @pytest.mark.parametrize(
        "clothes, expected",
        [
            # 기본 케이스 1
            ([["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]], 5),
            # 기본 케이스 2
            ([["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]], 3),
            # 엣지 케이스 (옷이 단 1개일 때)
            ([["shirt", "top"]], 1),
        ]
    )
    def test_basic(self, clothes, expected):
        """기본 케이스 및 엣지 케이스"""
        assert solution(clothes) == expected

    def test_worst_case_benchmark(self, benchmark):
        """최악의 케이스 벤치마크 (종류가 모두 다른 대량의 입력)"""
        # type_0 ~ type_999 까지 1000개의 서로 다른 종류의 옷 생성
        worst_case = [[f"cloth_{i}", f"type_{i}"] for i in range(1000)]

        # 벤치마크 실행 (solution 함수에 worst_case를 인자로 넘김)
        benchmark(solution, worst_case)
