#!/bin/bash
set -euo pipefail

# ============================================================
# new_problem.sh - 새 문제 템플릿 자동 생성
# 사용법: ./scripts/new_problem.sh <출처> <번호> <문제이름> [난이도] [태그]
# 예시:
#   ./scripts/new_problem.sh boj 1234 "두 포인터" gold3 "two-pointer,sliding-window"
#   ./scripts/new_problem.sh pg 42586 "기능개발" lv2 "queue,stack"
#   ./scripts/new_problem.sh lc 1 "Two Sum" easy "hash-map,array"
# ============================================================

if [ $# -lt 3 ]; then
    echo "사용법: $0 <출처> <번호> <문제이름> [난이도] [태그]"
    echo ""
    echo "출처: boj(백준), pg(프로그래머스), lc(LeetCode)"
    echo "예시: $0 boj 1234 \"두 포인터\" gold3 \"two-pointer,sliding-window\""
    exit 1
fi

SOURCE=$(echo "$1" | tr '[:upper:]' '[:lower:]')
NUMBER="$2"
TITLE="$3"
DIFFICULTY="${4:-}"
TAGS="${5:-}"

# 프로젝트 루트 경로 (스크립트 위치 기준)
ROOT="$(cd "$(dirname "$0")/.." && pwd)"

# 문제 ID 생성
PROBLEM_ID="${SOURCE}${NUMBER}"

# 출처별 URL 생성
case "$SOURCE" in
    boj) URL="https://www.acmicpc.net/problem/${NUMBER}" ;;
    pg)  URL="https://programmers.co.kr/learn/courses/30/lessons/${NUMBER}" ;;
    lc)  URL="https://leetcode.com/problems/" ;;
    *)   URL="" ;;
esac

# Java 패키지명 (숫자로 시작할 수 없으므로 출처 접두어 유지)
JAVA_PACKAGE="problems.${PROBLEM_ID}"
JAVA_DIR="${ROOT}/java/src/main/java/problems/${PROBLEM_ID}"
JAVA_TEST_DIR="${ROOT}/java/src/test/java/problems/${PROBLEM_ID}"

# Go / Python 경로
GO_DIR="${ROOT}/go/problems/${PROBLEM_ID}"
PY_DIR="${ROOT}/python/problems/${PROBLEM_ID}"

# 이미 존재하는지 확인
if [ -d "$JAVA_DIR" ] || [ -d "$GO_DIR" ] || [ -d "$PY_DIR" ]; then
    echo "❌ 이미 존재하는 문제입니다: ${PROBLEM_ID}"
    exit 1
fi

SOURCE_UPPER=$(echo "$SOURCE" | tr '[:lower:]' '[:upper:]')
echo "📝 새 문제 생성: [${SOURCE_UPPER}][${NUMBER}] ${TITLE}"

# ─── Java ───────────────────────────────────────────────
mkdir -p "$JAVA_DIR" "$JAVA_TEST_DIR"

cat > "${JAVA_DIR}/Solution.java" << JAVA_EOF
package problems.${PROBLEM_ID};

/**
 * [${SOURCE_UPPER}] ${NUMBER} - ${TITLE}
 * ${URL}
 * 난이도: ${DIFFICULTY}
 * 태그: ${TAGS}
 *
 * 시간복잡도: O(?)
 * 공간복잡도: O(?)
 */
public class Solution {

    public int solve() {
        throw new UnsupportedOperationException("풀이를 작성하세요");
    }
}
JAVA_EOF

cat > "${JAVA_TEST_DIR}/SolutionTest.java" << JAVA_TEST_EOF
package problems.${PROBLEM_ID};

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[${SOURCE_UPPER}] ${NUMBER} - ${TITLE}")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        // given

        // when
        int result = solution.solve();

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("엣지 케이스")
    void testEdge() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("큰 입력")
    void testLargeInput() {
        // given

        // when

        // then
    }
}
JAVA_TEST_EOF

echo "  ✅ Java 템플릿 생성 완료"

# ─── Go ─────────────────────────────────────────────────
mkdir -p "$GO_DIR"

cat > "${GO_DIR}/solution.go" << GO_EOF
// Package ${PROBLEM_ID} - [${SOURCE_UPPER}] ${NUMBER} - ${TITLE}
// ${URL}
// 난이도: ${DIFFICULTY}
// 태그: ${TAGS}
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package ${PROBLEM_ID}

func Solve() int {
	panic("not implemented")
}
GO_EOF

cat > "${GO_DIR}/solution_test.go" << GO_TEST_EOF
package ${PROBLEM_ID}

import "testing"

func TestSolve(t *testing.T) {
	tests := []struct {
		name string
		want int
	}{
		{
			name: "기본 케이스",
			want: 0,
		},
		{
			name: "엣지 케이스",
			want: 0,
		},
		{
			name: "큰 입력",
			want: 0,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := Solve()
			if got != tt.want {
				t.Errorf("Solve() = %v, want %v", got, tt.want)
			}
		})
	}
}

func BenchmarkSolve(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Solve()
	}
}
GO_TEST_EOF

echo "  ✅ Go 템플릿 생성 완료"

# ─── Python ─────────────────────────────────────────────
mkdir -p "$PY_DIR"

cat > "${PY_DIR}/__init__.py" << 'INIT_EOF'
INIT_EOF

cat > "${PY_DIR}/solution.py" << PY_EOF
"""
[${SOURCE_UPPER}] ${NUMBER} - ${TITLE}
${URL}
난이도: ${DIFFICULTY}
태그: ${TAGS}

시간복잡도: O(?)
공간복잡도: O(?)
"""


def solve() -> int:
    raise NotImplementedError("풀이를 작성하세요")
PY_EOF

cat > "${PY_DIR}/test_solution.py" << PY_TEST_EOF
import pytest
from .solution import solve


class TestSolve:
    """[${SOURCE_UPPER}] ${NUMBER} - ${TITLE}"""

    def test_basic(self):
        """기본 케이스"""
        assert solve() == 0

    def test_edge(self):
        """엣지 케이스"""
        pass

    def test_large_input(self):
        """큰 입력"""
        pass
PY_TEST_EOF

echo "  ✅ Python 템플릿 생성 완료"

# ─── README 업데이트 ────────────────────────────────────
"${ROOT}/scripts/update_readme.sh"

echo ""
echo "🎉 완료! ${PROBLEM_ID} 문제가 생성되었습니다."
echo ""
echo "  Java:   java/src/main/java/problems/${PROBLEM_ID}/Solution.java"
echo "  Go:     go/problems/${PROBLEM_ID}/solution.go"
echo "  Python: python/problems/${PROBLEM_ID}/solution.py"