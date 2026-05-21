#!/bin/bash
set -euo pipefail

# ============================================================
# update_readme_java.sh - Java 전용 README.md 문제 인덱스 생성
# ============================================================

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
README="${ROOT}/README.md"
JAVA_ROOT="${ROOT}/java"
JAVA_MAIN_DIR="${JAVA_ROOT}/src/main/java/problems"

leetcode_slug() {
    printf '%s' "$1" \
        | sed -E 's/^\[[^]]+\][[:space:]]*[0-9]+[[:space:]]*-[[:space:]]*//' \
        | tr '[:upper:]' '[:lower:]' \
        | sed -E 's/[^a-z0-9]+/-/g; s/^-+//; s/-+$//'
}

check_java() {
    local problem_id="$1"

    if [ ! -f "${JAVA_MAIN_DIR}/${problem_id}/Solution.java" ]; then
        echo "❌"
        return
    fi

    if [ ! -f "${JAVA_ROOT}/src/test/java/problems/${problem_id}/SolutionTest.java" ]; then
        echo "❌"
        return
    fi

    if (cd "${JAVA_ROOT}" && ./gradlew test --tests "problems.${problem_id}.SolutionTest" -q >/dev/null 2>&1); then
        echo "✅"
    else
        echo "❌"
    fi
}

PROBLEM_IDS=""
if [ -d "${JAVA_MAIN_DIR}" ]; then
    for dir in "${JAVA_MAIN_DIR}"/*/; do
        [ ! -d "$dir" ] && continue
        pid=$(basename "$dir")
        [ "$pid" = "*" ] && continue
        PROBLEM_IDS="${PROBLEM_IDS} ${pid}"
    done
fi

PROBLEM_IDS=$(echo "$PROBLEM_IDS" | tr ' ' '\n' | sort | tr '\n' ' ')
total=$(echo "$PROBLEM_IDS" | tr ' ' '\n' | grep -c '[a-z]' || true)

cat > "$README" << HEADER
# 🧮 Algorithm Practice

코딩 테스트 준비 - Java 전용 풀이

## 📊 진행 현황

| 총 문제 수 |
|:---:|
| **${total}** |

## 📋 문제 목록

| # | 출처 | 번호 | 문제 | 난이도 | Java | 태그 |
|---|------|------|------|--------|------|------|
HEADER

idx=1
for problem_id in $PROBLEM_IDS; do
    [ -z "$problem_id" ] && continue

    java_file="${JAVA_MAIN_DIR}/${problem_id}/Solution.java"
    title=""
    difficulty=""
    tags=""

    if [ -f "$java_file" ]; then
        title=$(sed -n 's/^ \* \[\([A-Z]\+\)\] \([0-9]\+\) - \(.*\)$/[\1] \2 - \3/p' "$java_file" | head -1)
        difficulty=$(sed -n 's/^ \* 난이도: \(.*\)$/\1/p' "$java_file" | head -1)
        tags=$(sed -n 's/^ \* 태그: \(.*\)$/\1/p' "$java_file" | head -1)
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
        lc)  link="[${number}](https://leetcode.com/problems/$(leetcode_slug "${title}")/)"; src_label="LC" ;;
        *)   link="${number}"; src_label=$(echo "$source" | tr '[:lower:]' '[:upper:]') ;;
    esac

    java_status=$(check_java "$problem_id")

    echo "| ${idx} | ${src_label} | ${link} | ${title} | ${difficulty} | ${java_status} | ${tags} |" >> "$README"
    idx=$((idx + 1))
done

if [ "$total" -eq 0 ]; then
    echo "| - | - | - | 아직 풀이가 없습니다 | - | - | - |" >> "$README"
fi

cat >> "$README" << 'FOOTER'

## 🚀 시작하기

```bash
# 새 Java 문제 추가
./scripts/new_problem_java.sh boj 1234 "문제이름" gold3 "태그1,태그2"

# Java 테스트 실행
cd java && ./gradlew test
```

## 📁 프로젝트 구조

```text
├── java/        ← Gradle + JUnit 5
├── docs/        ← 학습 메모
├── skills/      ← Codex 스킬
└── scripts/     ← 자동화 스크립트
```
FOOTER

echo "  📄 Java 전용 README.md 업데이트 완료 (총 ${total}문제)"
