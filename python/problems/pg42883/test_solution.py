import pytest
# 동일 경로에 solution.py가 있다고 가정합니다.
# 만약 파일명이 다르다면 import문을 수정하세요.
from .solution import solution

class TestSolution:
    """[PG] 42883 - 큰 수 만들기"""

    def test_basic(self):
        """기본 입출력 예시 케이스"""
        assert solution("1924", 2) == "94"
        assert solution("1231234", 3) == "3234"
        assert solution("4177252841", 4) == "775841"

    def test_edge(self):
        """엣지 케이스: 숫자가 내림차순이거나 모두 같은 경우"""
        # 내림차순일 때: 뒤의 숫자를 제거해야 함
        assert solution("9876", 2) == "98"
        # 모두 같은 숫자일 때
        assert solution("1111", 2) == "11"
        # k가 1인 최소 경우
        assert solution("10", 1) == "1"

    def test_large_input(self):
        """큰 입력: 1,000,000자리 숫자 처리 확인"""
        # 1이 50만개, 2가 50만개 있고 k가 50만인 경우
        number = "1" * 500000 + "2" * 500000
        k = 500000
        # 앞의 '1'들이 모두 제거되고 '2'만 50만개 남아야 함
        assert solution(number, k) == "2" * 500000
