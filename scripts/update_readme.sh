#!/bin/bash
set -euo pipefail

# ============================================================
# update_readme.sh - README.md 문제 인덱스 자동 생성
# ============================================================

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
README="${ROOT}/README.md"

# ─── 언어별 풀이 상태 확인 (테스트 통과 여부) ──────────────────
check_lang() {
    local problem_id="$1"
    local java_status="❌"
    local go_status="❌"
    local python_status="❌"

    if [ -f "${ROOT}/java/src/main/java/problems/${problem_id}/Solution.java" ]; then
        if (cd "${ROOT}/java" && ./gradlew test --tests "problems.${problem_id}.SolutionTest" -q >/dev/null 2>&1); then
            java_status="✅"
        fi
    fi

    if [ -f "${ROOT}/go/problems/${problem_id}/solution.go" ]; then
        if (cd "${ROOT}/go" && go test "./problems/${problem_id}/" >/dev/null 2>&1); then
            go_status="✅"
        fi
    fi

    if [ -f "${ROOT}/python/problems/${problem_id}/solution.py" ]; then
        if (cd "${ROOT}/python" && python3 -m pytest "problems/${problem_id}/" -q >/dev/null 2>&1); then
            python_status="✅"
        fi
    fi

    echo "${java_status}|${go_status}|${python_status}"
}

# ─── 문제 목록 수집 (Go 디렉토리 기준) ────────────────────
PROBLEM_IDS=""
if [ -d "${ROOT}/go/problems" ]; then
    for dir in "${ROOT}/go/problems"/*/; do
        [ ! -d "$dir" ] && continue
        pid=$(basename "$dir")
        [ "$pid" = "*" ] && continue
        PROBLEM_IDS="${PROBLEM_IDS} ${pid}"
    done
fi

# 정렬
PROBLEM_IDS=$(echo "$PROBLEM_IDS" | tr ' ' '\n' | sort | tr '\n' ' ')
total=$(echo "$PROBLEM_IDS" | tr ' ' '\n' | grep -c '[a-z]' || true)

# ─── README 생성 ────────────────────────────────────────
cat > "$README" << HEADER
# 🧮 Algorithm Practice

코딩 테스트 준비 - Java, Go, Python 멀티 언어 풀이

## 📊 진행 현황

| 총 문제 수 |
|:---:|
| **${total}** |

## 📋 문제 목록

| # | 출처 | 번호 | 문제 | 난이도 | Java | Go | Python | 태그 |
|---|------|------|------|--------|------|----|--------|------|
HEADER

idx=1
for problem_id in $PROBLEM_IDS; do
    [ -z "$problem_id" ] && continue

    # 메타 정보 추출 (Go 파일에서)
    go_file="${ROOT}/go/problems/${problem_id}/solution.go"
    title=""
    difficulty=""
    tags=""
    if [ -f "$go_file" ]; then
        title=$(grep -oE ' - .+' "$go_file" | head -1 | sed 's/ - //' || echo "")
        difficulty=$(grep -oE '난이도: .+' "$go_file" | sed 's/난이도: //' || echo "")
        tags=$(grep -oE '태그: .+' "$go_file" | sed 's/태그: //' || echo "")
    fi

    # 출처, 번호 파싱
    if echo "$problem_id" | grep -qE '^(boj|pg|lc)[0-9]+$'; then
        source=$(echo "$problem_id" | sed 's/[0-9]//g')
        number=$(echo "$problem_id" | sed 's/[^0-9]//g')
    else
        source="unknown"
        number="$problem_id"
    fi

    # 출처별 링크
    case "$source" in
        boj) link="[${number}](https://www.acmicpc.net/problem/${number})" ; src_label="BOJ" ;;
        pg)  link="[${number}](https://programmers.co.kr/learn/courses/30/lessons/${number})" ; src_label="PG" ;;
        lc)  link="[${number}](https://leetcode.com/problems/)" ; src_label="LC" ;;
        *)   link="${number}" ; src_label=$(echo "$source" | tr '[:lower:]' '[:upper:]') ;;
    esac

    lang_status=$(check_lang "$problem_id")
    java_s=$(echo "$lang_status" | cut -d'|' -f1)
    go_s=$(echo "$lang_status" | cut -d'|' -f2)
    python_s=$(echo "$lang_status" | cut -d'|' -f3)

    echo "| ${idx} | ${src_label} | ${link} | ${title} | ${difficulty} | ${java_s} | ${go_s} | ${python_s} | ${tags} |" >> "$README"
    idx=$((idx + 1))
done

# 테이블이 비어있으면 안내 메시지
if [ "$total" -eq 0 ]; then
    echo "| - | - | - | 아직 풀이가 없습니다 | - | - | - | - | - |" >> "$README"
fi

cat >> "$README" << 'FOOTER'

## 🚀 시작하기

```bash
# 새 문제 추가
./scripts/new_problem.sh boj 1234 "문제이름" gold3 "태그1,태그2"

# 테스트 실행
cd java && ./gradlew test                    # Java
cd go && go test ./problems/...              # Go
cd python && pytest                          # Python
```

## 📁 프로젝트 구조

```
├── java/       ← Gradle + JUnit 5
├── go/         ← Go modules + testing
├── python/     ← pytest
└── scripts/    ← 자동화 스크립트
```
FOOTER

echo "  📄 README.md 업데이트 완료 (총 ${total}문제)"
