#!/bin/bash
set -euo pipefail

# ============================================================
# update_readme.sh - README.md 문제 인덱스 자동 생성
# 문제 중심으로 정리하여 한눈에 진행 상황을 파악할 수 있도록 함
# ============================================================

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
README="${ROOT}/README.md"

# ─── 문제 목록 수집 ────────────────────────────────────
declare -A PROBLEMS=()  # problem_id -> "source|number|title|difficulty|tags"

# Go 디렉토리 기준으로 문제 수집 (모든 언어에 동일하게 존재하므로)
for lang_dir in "${ROOT}/go/problems" "${ROOT}/java/src/main/java/problems" "${ROOT}/python/problems"; do
    if [ ! -d "$lang_dir" ]; then continue; fi
    for dir in "$lang_dir"/*/; do
        [ ! -d "$dir" ] && continue
        problem_id=$(basename "$dir")
        [ "$problem_id" = "*" ] && continue

        if [ -z "${PROBLEMS[$problem_id]+x}" ]; then
            # 메타 정보 추출 (Go 파일에서)
            go_file="${ROOT}/go/problems/${problem_id}/solution.go"
            if [ -f "$go_file" ]; then
                title=$(grep -oP '(?<=- ).*' "$go_file" | head -1 || echo "")
                difficulty=$(grep -oP '(?<=난이도: ).*' "$go_file" || echo "")
                tags=$(grep -oP '(?<=태그: ).*' "$go_file" || echo "")
            else
                title=""
                difficulty=""
                tags=""
            fi

            # 출처, 번호 파싱
            if [[ "$problem_id" =~ ^(boj|pg|lc)([0-9]+)$ ]]; then
                source="${BASH_REMATCH[1]}"
                number="${BASH_REMATCH[2]}"
            else
                source="unknown"
                number="$problem_id"
            fi

            PROBLEMS[$problem_id]="${source}|${number}|${title}|${difficulty}|${tags}"
        fi
    done
done

# ─── 언어별 풀이 상태 확인 ──────────────────────────────
check_lang() {
    local problem_id="$1"
    local java_exists="❌"
    local go_exists="❌"
    local python_exists="❌"

    [ -f "${ROOT}/java/src/main/java/problems/${problem_id}/Solution.java" ] && java_exists="✅"
    [ -f "${ROOT}/go/problems/${problem_id}/solution.go" ] && go_exists="✅"
    [ -f "${ROOT}/python/problems/${problem_id}/solution.py" ] && python_exists="✅"

    echo "${java_exists}|${go_exists}|${python_exists}"
}

# ─── 통계 계산 ──────────────────────────────────────────
total=${#PROBLEMS[@]}

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

# 정렬하여 출력
idx=1
for problem_id in $(echo "${!PROBLEMS[@]:-}" | tr ' ' '\n' | sort); do
    [ -z "$problem_id" ] && continue
    IFS='|' read -r source number title difficulty tags <<< "${PROBLEMS[$problem_id]}"
    IFS='|' read -r java go python <<< "$(check_lang "$problem_id")"

    # 출처별 링크
    case "$source" in
        boj) link="[${number}](https://www.acmicpc.net/problem/${number})" ; src_label="BOJ" ;;
        pg)  link="[${number}](https://programmers.co.kr/learn/courses/30/lessons/${number})" ; src_label="PG" ;;
        lc)  link="[${number}](https://leetcode.com/problems/)" ; src_label="LC" ;;
        *)   link="${number}" ; src_label="${source^^}" ;;
    esac

    echo "| ${idx} | ${src_label} | ${link} | ${title} | ${difficulty} | ${java} | ${go} | ${python} | ${tags} |" >> "$README"
    ((idx++))
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