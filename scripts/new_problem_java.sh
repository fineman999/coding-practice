#!/bin/bash
set -euo pipefail

# ============================================================
# new_problem_java.sh - Java 전용 문제 템플릿 생성
# 사용법: ./scripts/new_problem_java.sh <출처> <번호> <문제이름> [난이도] [태그]
# 예시:
#   ./scripts/new_problem_java.sh boj 1234 "두 포인터" gold3 "two-pointer,sliding-window"
#   ./scripts/new_problem_java.sh pg 42586 "기능개발" lv2 "queue,stack"
# ============================================================

if [ $# -lt 3 ]; then
    echo "사용법: $0 <출처> <번호> <문제이름> [난이도] [태그]"
    echo ""
    echo "출처: boj(백준), pg(프로그래머스), lc(LeetCode)"
    echo "예시: $0 pg 42586 \"기능개발\" lv2 \"queue,stack\""
    exit 1
fi

SOURCE=$(echo "$1" | tr '[:upper:]' '[:lower:]')
NUMBER="$2"
TITLE="$3"
DIFFICULTY="${4:-}"
TAGS="${5:-}"

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PROBLEM_ID="${SOURCE}${NUMBER}"

case "$SOURCE" in
    boj) URL="https://www.acmicpc.net/problem/${NUMBER}" ;;
    pg)  URL="https://programmers.co.kr/learn/courses/30/lessons/${NUMBER}" ;;
    lc)  URL="https://leetcode.com/problems/" ;;
    *)   URL="" ;;
esac

JAVA_DIR="${ROOT}/java/src/main/java/problems/${PROBLEM_ID}"
JAVA_TEST_DIR="${ROOT}/java/src/test/java/problems/${PROBLEM_ID}"
if [ -d "$JAVA_DIR" ] || [ -d "$JAVA_TEST_DIR" ]; then
    echo "❌ 이미 존재하는 Java 문제입니다: ${PROBLEM_ID}"
    exit 1
fi

SOURCE_UPPER=$(echo "$SOURCE" | tr '[:lower:]' '[:upper:]')
echo "📝 Java 전용 새 문제 생성: [${SOURCE_UPPER}][${NUMBER}] ${TITLE}"

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

    public int solution() {
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
        int result = solution.solution();

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
echo "  ✅ Java main/test 템플릿 생성 완료"
