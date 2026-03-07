import random

import pytest
from .solution import solution


class TestSolution:
    """[PG] 86491 - 최소직사각형"""

    # @pytest.mark.parametrize를 사용하면 여러 케이스를 한 번에 깔끔하게 테스트할 수 있습니다.
    @pytest.mark.parametrize(
        "sizes, expected",
        [
            # 1. 기본 케이스
            (
                    [[60, 50], [30, 70], [60, 30], [80, 40]],
                    4000
            ),
            # 2. 엣지 케이스: 배열 길이 1, i=j
            (
                    [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]],
                    120
            ),
            # 3. 엣지 케이스: 이미 정렬된 배열을 처음부터 끝까지 자를 때
            (
                    [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]],
                    133
            ),
        ]
    )
    def test_basic(self, sizes, expected):
        """기본 케이스"""
        assert solution(sizes) == expected

    # ---------------------------------------------------------
    # 🚀 벤치마크 테스트 (Go의 BenchmarkSolve, Java의 JMH 역할)
    # ---------------------------------------------------------
    def test_large_input_benchmark(self, benchmark):
        """큰 입력에 대한 성능 측정"""
        random.seed(42)  # 재현 가능하도록 시드 고정
        sizes = [[random.randint(1, 1000), random.randint(1, 1000)] for _ in range(10_000)]

        # 기대값을 직접 계산
        expected = max(max(w, h) for w, h in sizes) * max(min(w, h) for w, h in sizes)

        result = benchmark(solution, sizes)
        assert result == expected