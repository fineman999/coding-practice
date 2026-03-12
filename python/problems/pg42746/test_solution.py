import pytest
from .solution import solution, solution02


class TestSolution:
    """[PG] 42746 - 가장 큰 수"""

    @pytest.mark.parametrize(
        "numbers, expected",
        [
            ([6, 10, 2], "6210"),
            ([3, 30, 34, 5, 9], "9534330"),
            ([0, 0, 0], "0"),
            ([96, 9, 91, 997, 8], "999796918"),
        ],
    )
    def test_basic(self, numbers, expected):
        """기본 케이스"""
        # 원본 리스트를 훼손하지 않도록 복사해서 전달
        assert solution02(numbers.copy()) == expected

    def test_large_input_benchmark(self, benchmark):
        """큰 입력에 대한 벤치마크 (pytest-benchmark 사용)"""
        # 크고 반복적인 입력을 만듭니다. (문제 제약: 원소는 0~1000)
        numbers = [i % 1000 for i in range(10_000)]

        # benchmark가 solution을 호출하여 실행 시간을 측정합니다.
        result = benchmark(solution02, numbers.copy())

        # 벤치마크 검증을 위해, 기대값을 독립적으로 생성합니다.
        # (solution 구현과 동일한 아이디어: 문자열을 3번 반복하여 정렬)
        numbers_str = list(map(str, numbers))
        expected = "".join(sorted(numbers_str, key=lambda x: x * 3, reverse=True))
        if expected and expected[0] == "0":
            expected = "0"

        assert result == expected
