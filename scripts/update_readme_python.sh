#!/bin/bash
set -euo pipefail

# ============================================================
# update_readme_python.sh - Python 전용 README.md 문제 인덱스 생성
# ============================================================

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
README="${ROOT}/README.md"
PY_ROOT="${ROOT}/python"
PY_MAIN_DIR="${PY_ROOT}/problems"
PYTHON_BIN="python3"
PYTHON_TEST_RUNNER_STATUS="ready"
PYTHON_TEST_RUNNER_NOTE=""

if [ -x "${PY_ROOT}/.venv/bin/python" ]; then
    PYTHON_BIN="${PY_ROOT}/.venv/bin/python"
elif [ -x "${ROOT}/.venv/bin/python" ]; then
    PYTHON_BIN="${ROOT}/.venv/bin/python"
fi

if ! command -v "${PYTHON_BIN}" >/dev/null 2>&1 && [ ! -x "${PYTHON_BIN}" ]; then
    PYTHON_TEST_RUNNER_STATUS="missing-python"
    PYTHON_TEST_RUNNER_NOTE="python3 가 없어 Python 테스트 판정을 건너뜁니다."
elif ! "${PYTHON_BIN}" -c "import pytest" >/dev/null 2>&1; then
    PYTHON_TEST_RUNNER_STATUS="missing-pytest"
    PYTHON_TEST_RUNNER_NOTE="${PYTHON_BIN} 환경에 pytest 가 없어 Python 테스트 판정을 건너뜁니다."
fi

check_python() {
    local problem_id="$1"

    if [ ! -f "${PY_MAIN_DIR}/${problem_id}/solution.py" ]; then
        echo "❌"
        return
    fi

    case "${PYTHON_TEST_RUNNER_STATUS}" in
        ready)
            if (cd "${PY_ROOT}" && "${PYTHON_BIN}" -m pytest "problems/${problem_id}/" -q >/dev/null 2>&1); then
                echo "✅"
            else
                echo "❌"
            fi
            ;;
        *)
            echo "⚠️"
            ;;
    esac
}

PROBLEM_IDS=""
if [ -d "${PY_MAIN_DIR}" ]; then
    for dir in "${PY_MAIN_DIR}"/*/; do
        [ ! -d "$dir" ] && continue
        pid=$(basename "$dir")
        [ "$pid" = "__pycache__" ] && continue
        PROBLEM_IDS="${PROBLEM_IDS} ${pid}"
    done
fi

PROBLEM_IDS=$(echo "$PROBLEM_IDS" | tr ' ' '\n' | sort | tr '\n' ' ')
total=$(echo "$PROBLEM_IDS" | tr ' ' '\n' | grep -c '[a-z]' || true)

cat > "$README" << HEADER
# 🧮 Algorithm Practice

코딩 테스트 준비 - Python 전용 풀이

## 📊 진행 현황

| 총 문제 수 |
|:---:|
| **${total}** |

## 📋 문제 목록

| # | 출처 | 번호 | 문제 | 난이도 | Python | 태그 |
|---|------|------|------|--------|--------|------|
HEADER

idx=1
for problem_id in $PROBLEM_IDS; do
    [ -z "$problem_id" ] && continue

    py_file="${PY_MAIN_DIR}/${problem_id}/solution.py"
    title=""
    difficulty=""
    tags=""

    if [ -f "$py_file" ]; then
        title=$(sed -n 's/^\[\([A-Z]\+\)\] \([0-9]\+\) - \(.*\)$/[\1] \2 - \3/p' "$py_file" | head -1)
        difficulty=$(sed -n 's/^난이도: \(.*\)$/\1/p' "$py_file" | head -1)
        tags=$(sed -n 's/^태그: \(.*\)$/\1/p' "$py_file" | head -1)
    fi

    if echo "$problem_id" | grep -qE '^(boj|pg|lc)[0-9]+$'; then
        source=$(echo "$problem_id" | sed 's/[0-9]//g')
        number=$(echo "$problem_id" | sed 's/[^0-9]//g')
    else
        source="unknown"
        number="$problem_id"
    fi

    case "$source" in
        boj) link="[${number}](https://www.acmicpc.net/problem/${number})"; src_label="BOJ" ;;
        pg)  link="[${number}](https://programmers.co.kr/learn/courses/30/lessons/${number})"; src_label="PG" ;;
        lc)  link="[${number}](https://leetcode.com/problems/)"; src_label="LC" ;;
        *)   link="${number}"; src_label=$(echo "$source" | tr '[:lower:]' '[:upper:]') ;;
    esac

    python_status=$(check_python "$problem_id")

    echo "| ${idx} | ${src_label} | ${link} | ${title} | ${difficulty} | ${python_status} | ${tags} |" >> "$README"
    idx=$((idx + 1))
done

if [ "$total" -eq 0 ]; then
    echo "| - | - | - | 아직 풀이가 없습니다 | - | - | - |" >> "$README"
fi

cat >> "$README" << 'FOOTER'

## 🚀 시작하기

```bash
# 새 Python 문제 추가
./scripts/new_problem_python.sh boj 1234 "문제이름" gold3 "태그1,태그2"

# Python 테스트 실행
cd python && pytest
```

## 📁 프로젝트 구조

```text
├── python/      ← pytest
├── docs/        ← 학습 메모
├── skills/      ← Codex 스킬
└── scripts/     ← 자동화 스크립트
```
FOOTER

echo "  📄 Python 전용 README.md 업데이트 완료 (총 ${total}문제)"

if [ "${PYTHON_TEST_RUNNER_STATUS}" != "ready" ]; then
    echo "  ⚠️  ${PYTHON_TEST_RUNNER_NOTE}"
fi
