import pytest
from .solution import solution


class TestSolution:
    """[PG] 42577 - 전화번호 목록"""

    @pytest.mark.parametrize(
        "phone_book, expected",
        [
            (
                ["119", "97674223", "1195524421"], False
            ),
            (
                ["123", "456", "789"], True,
            ),
            (
                ["12", "123", "1235", "567", "88"], False
            )
        ]
    )
    def test_basic(self, phone_book, expected):
        """기본 케이스"""
        assert solution(phone_book)==expected


    def test_best_case_benchmark(self, benchmark):
        """최선의 케이스 벤치마크 (앞부분에서 바로 접두어 발견)"""
        # "12"와 "123"을 맨 앞에 두고 나머지는 무의미한 더미 데이터 10,000개 추가
        # 정렬 직후 첫 번째 비교에서 바로 return False가 터져야 하는 환경입니다.
        phone_book = ["12", "123"] + [str(1000000 + i) for i in range(10000)]

        # 벤치마크 실행
        result = benchmark(solution, phone_book)

        # 접두어를 발견했으므로 False를 반환해야 함
        assert result is False