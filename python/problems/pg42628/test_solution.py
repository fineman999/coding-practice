import pytest
from .solution import solution

class TestSolution:
    """[PG] 42628 - 이중우선순위큐"""

    def test_basic(self):
        """기본 케이스 (입출력 예시)"""
        # 예시 1
        ops1 = ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]
        assert solution(ops1) == [0, 0]

        # 예시 2
        ops2 = ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]
        assert solution(ops2) == [333, -45]

    def test_edge(self):
        """엣지 케이스: 빈 큐 삭제, 동일 값 중복 등"""
        # 빈 큐에서 삭제 시도
        assert solution(["D 1", "D -1"]) == [0, 0]

        # 동일한 숫자가 여러 개일 때 하나만 삭제되는지
        assert solution(["I 10", "I 10", "I 10", "D 1"]) == [10, 10]

        # 모든 원소 삭제 후 다시 삽입
        assert solution(["I 1", "D 1", "I 5"]) == [5, 5]

    def test_large_input(self):
        """큰 입력: 최대 데이터 개수 처리 확인"""
        # 1,000,000개까지 들어올 수 있으므로 효율성 체크 (간단한 반복 삽입)
        ops = ["I 1"] * 10000 + ["D 1"] * 5000
        result = solution(ops)
        assert result == [1, 1]
