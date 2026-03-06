import pytest
from .solution import solve

class TestSolve:
    """[PG] 42748 - k번째수"""

    # @pytest.mark.parametrize를 사용하면 여러 케이스를 한 번에 깔끔하게 테스트할 수 있습니다.
    @pytest.mark.parametrize(
        "array, commands, expected",
        [
            # 1. 기본 케이스
            (
                    [1, 5, 2, 6, 3, 7, 4],
                    [[2, 5, 3], [4, 4, 1], [1, 7, 3]],
                    [5, 6, 3]
            ),
            # 2. 엣지 케이스: 배열 길이 1, i=j
            (
                    [10],
                    [[1, 1, 1]],
                    [10]
            ),
            # 3. 엣지 케이스: 이미 정렬된 배열을 처음부터 끝까지 자를 때
            (
                    [1, 2, 3, 4, 5],
                    [[1, 5, 3]],
                    [3]
            ),
        ]
    )
    def test_basic_and_edge(self, array, commands, expected):
        """기본 및 엣지 케이스"""
        assert solve(array, commands) == expected

    def test_large_input(self):
        """큰 입력 (최대 길이, 전체 복사 및 정렬 최악의 케이스)"""
        # 파이썬은 리스트 컴프리헨션과 내장 함수로 헬퍼 함수 없이도 쉽게 동적 생성이 가능합니다.

        # 100부터 1까지 역순 배열 생성 [100, 99, ..., 1]
        array = list(range(100, 0, -1))

        # [1, 100, 50] 명령을 50개 생성
        commands = [[1, 100, 50] for _ in range(50)]

        # 정답 50이 50개 들어있는 배열 생성
        expected = [50] * 50

        assert solve(array, commands) == expected

    # ---------------------------------------------------------
    # 🚀 벤치마크 테스트 (Go의 BenchmarkSolve, Java의 JMH 역할)
    # ---------------------------------------------------------
    def test_large_input_benchmark(self, benchmark):
        """큰 입력에 대한 성능 측정"""

        # 1. 벤치마크 준비 (Go의 셋업, Java의 @Setup 역할)
        array = list(range(100, 0, -1))
        commands = [[1, 100, 50] for _ in range(50)]
        expected = [50] * 50

        # 2. 측정 시작! (이 한 줄이 핵심입니다)
        # benchmark(실행할함수, 인자1, 인자2, ...) 형태로 호출하면
        # 알아서 수천 번 반복 실행하며 시간을 측정하고 결과를 반환합니다.
        result = benchmark(solve, array, commands)

        # 3. 측정된 결과가 맞는지 검증까지 한 번에 처리
        assert result == expected