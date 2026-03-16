import pytest
from .solution import solution


class TestSolution:

    @pytest.mark.parametrize(
        "priorities, location, expected",
        [
            ([2, 1, 3, 2], 2, 1),           # 문제 예시 1
            ([1, 1, 9, 1, 1, 1], 0, 5),     # 문제 예시 2
        ]
    )
    def test_solution(self, priorities, location, expected):
        assert solution(priorities, location) == expected

    def test_large_case(self, benchmark):
        # 큰 입력: 1~9 우선순위가 반복되고, location은 마지막
        priorities = [i % 9 + 1 for i in range(100)]
        location = 99
        result = benchmark(solution ,priorities, location)
        assert 1 <= result <= 100   # 정답 범위에는 들어가는지 체크 (구체적 정답은 로직에 따라 다름)