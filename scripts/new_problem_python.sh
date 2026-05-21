#!/bin/bash
set -euo pipefail

# ============================================================
# new_problem_python.sh - Python 전용 문제 템플릿 생성
# 사용법:
#   ./scripts/new_problem_python.sh <출처> <번호> <문제이름> [난이도] [태그]
#   ./scripts/new_problem_python.sh <구름 URL> [난이도] [태그]
# 예시:
#   ./scripts/new_problem_python.sh boj 1234 "두 포인터" gold3 "two-pointer,sliding-window"
#   ./scripts/new_problem_python.sh pg 42586 "기능개발" lv2 "queue,stack"
#   ./scripts/new_problem_python.sh "https://level.goorm.io/exam/194982/%EC%9E%A5%EB%A7%88/quiz/1" lv1 "implementation"
# ============================================================

urldecode() {
    local encoded="${1//+/ }"
    printf '%b' "${encoded//%/\\x}"
}

if [[ "${1:-}" =~ ^https://level\.goorm\.io/exam/([0-9]+)/([^/]+)/quiz/([0-9]+)$ ]]; then
    SOURCE="grm"
    NUMBER="${BASH_REMATCH[1]}"
    TITLE="$(urldecode "${BASH_REMATCH[2]}")"
    URL="$1"
    DIFFICULTY="${2:-}"
    TAGS="${3:-}"
else
    if [ $# -lt 3 ]; then
        echo "사용법: $0 <출처> <번호> <문제이름> [난이도] [태그]"
        echo "또는:   $0 <구름 URL> [난이도] [태그]"
        echo ""
        echo "출처: boj(백준), pg(프로그래머스), lc(LeetCode), grm(구름)"
        echo "예시: $0 pg 42586 \"기능개발\" lv2 \"queue,stack\""
        echo "예시: $0 \"https://level.goorm.io/exam/194982/%EC%9E%A5%EB%A7%88/quiz/1\" lv1 \"implementation\""
        exit 1
    fi

    SOURCE=$(echo "$1" | tr '[:upper:]' '[:lower:]')
    NUMBER="$2"
    TITLE="$3"
    DIFFICULTY="${4:-}"
    TAGS="${5:-}"
fi

if [ -z "${SOURCE:-}" ] || [ -z "${NUMBER:-}" ] || [ -z "${TITLE:-}" ]; then
    echo "❌ 문제 정보를 해석하지 못했습니다."
    exit 1
fi

if [ -z "${URL:-}" ]; then
    case "$SOURCE" in
        boj) URL="https://www.acmicpc.net/problem/${NUMBER}" ;;
        pg)  URL="https://programmers.co.kr/learn/courses/30/lessons/${NUMBER}" ;;
        lc)  URL="https://leetcode.com/problems/" ;;
        grm) URL="https://level.goorm.io/exam/${NUMBER}" ;;
        *)   URL="" ;;
    esac
fi

if [[ ! "$NUMBER" =~ ^[0-9]+$ ]]; then
    echo "❌ 문제 번호는 숫자여야 합니다: ${NUMBER}"
    exit 1
fi

if [[ ! "$SOURCE" =~ ^(boj|pg|lc|grm)$ ]]; then
    echo "❌ 지원하지 않는 출처입니다: ${SOURCE}"
    echo ""
    echo "출처: boj(백준), pg(프로그래머스), lc(LeetCode), grm(구름)"
    exit 1
fi

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
PROBLEM_ID="${SOURCE}${NUMBER}"

PY_DIR="${ROOT}/python/problems/${PROBLEM_ID}"

if [ -d "$PY_DIR" ]; then
    echo "❌ 이미 존재하는 Python 문제입니다: ${PROBLEM_ID}"
    exit 1
fi

SOURCE_UPPER=$(echo "$SOURCE" | tr '[:lower:]' '[:upper:]')
echo "📝 Python 전용 새 문제 생성: [${SOURCE_UPPER}][${NUMBER}] ${TITLE}"

mkdir -p "$PY_DIR"

cat > "${PY_DIR}/solution.py" << PY_EOF
"""
[${SOURCE_UPPER}] ${NUMBER} - ${TITLE}
${URL}
난이도: ${DIFFICULTY}
태그: ${TAGS}

시간복잡도: O(?)
공간복잡도: O(?)
"""
PY_EOF

echo "  ✅ Python solution.py 껍데기 생성 완료"
