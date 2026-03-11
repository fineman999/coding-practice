import pytest
from .solution import solution

class TestSolution:
    """[PG] 42626 - 더 맵게"""

    @pytest.mark.parametrize(
        "scoville, k, expected",
        [
            ([1, 2, 3, 9, 10, 12], 7, 2),  # 기본 케이스
            ([1, 2, 3], 100, -1),          # 모두 섞어도 K를 넘을 수 없는 경우
            ([10, 20, 30], 5, 0),          # 처음부터 모두 K 이상인 경우
            ([1, 2], 5, 1),                # 원소가 2개뿐인 경우
            ([1, 2], 6, -1),               # 원소가 2개뿐인데 섞어도 K 미만인 경우
        ]
    )
    def test_basic(self, scoville, k, expected):
        """기본 케이스"""
        # heapq 함수들이 원본 리스트를 변형하므로 복사본을 넘겨주는 것이 안전합니다.
        assert solution(scoville[:], k) == expected


    def test_benchmark_large_input(self, benchmark):
        """최악의 조건(가장 연산량이 많은 큰 입력)에서의 성능 측정"""
        # 10만 개의 데이터 세팅 (최악의 경우를 상정)
        # 모두 1로 채우고 K를 아주 높게 잡아 배열이 1개가 남을 때까지 섞도록 유도
        large_scoville = [1] * 100000
        large_k = 1000000000

        # 벤치마크 실행 (solution 함수 내에서 원본이 훼손되므로,
        # benchmark에 원본 데이터를 그대로 넣지 않고 매번 얕은 복사가 이뤄지도록 처리해야 합니다)
        # pytest-benchmark는 람다나 래퍼 함수를 써서 복사본을 주입할 수 있습니다.
        def run_benchmark():
            return solution(large_scoville[:], large_k)

        result = benchmark(run_benchmark)

        assert result == -1  # 1을 10만 번 섞어도 10억을 넘을 수는 없으므로 -1 반환