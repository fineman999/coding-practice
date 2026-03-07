# 🧮 Algorithm Practice

코딩 테스트 준비 - Java, Go, Python 멀티 언어 풀이

## 📊 진행 현황

| 총 문제 수 |
|:---:|
| **2** |

## 📋 문제 목록

| # | 출처 | 번호 | 문제 | 난이도 | Java | Go | Python | 태그 |
|---|------|------|------|--------|------|----|--------|------|
| 1 | PG | [42748](https://programmers.co.kr/learn/courses/30/lessons/42748) | [PG] 42748 - k번째수 | lv1 | ✅ | ✅ | ✅ | sort,array |
| 2 | PG | [86491](https://programmers.co.kr/learn/courses/30/lessons/86491) | [PG] 86491 - 최소직사각형 | lv1 | ❌ | ❌ | ❌ | search |

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
