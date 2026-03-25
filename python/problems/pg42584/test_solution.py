import pytest
from .solution import solution

class TestSolution:
    @pytest.mark.parametrize(
        "prices, expected",
        [
            ([1, 2, 3, 2, 3], [4, 3, 1, 1, 0]),  # 문제 예시
            ([1, 2, 3, 4, 5], [4, 3, 2, 1, 0]),  # 계속 상승
            ([5, 4, 3, 2, 1], [1, 1, 1, 1, 0]),  # 계속 하락
            ([1, 2, 3, 1, 3], [4, 2, 1, 1, 0]),  # 아까 같이 본 케이스
        ]
    )
    def test_solution(self, prices, expected):
        assert solution(prices) == expected

    def test_large_case(self, benchmark):
        # 최대 입력 크기인 100,000개 데이터 테스트
        prices = [i % 10000 + 1 for i in range(100_000)]

        # 벤치마크 측정 및 결과 검증
        result = benchmark(solution, prices)

        assert len(result) == 100_000
        assert result[-1] == 0  # 마지막은 무조건 0초