# 🧮 Algorithm Practice

코딩 테스트 준비 - Java, Go, Python 멀티 언어 풀이

## 📊 진행 현황

| 총 문제 수 |
|:---:|
| **13** |

## 📋 문제 목록

| # | 출처 | 번호 | 문제 | 난이도 | Java | Go | Python | 태그 |
|---|------|------|------|--------|------|----|--------|------|
| 1 | PG | [12909](https://programmers.co.kr/learn/courses/30/lessons/12909) | [PG] 12909 - 올바른 괄호 | lv2 | ✅ | ✅ | ✅ | stack,queue |
| 2 | PG | [42577](https://programmers.co.kr/learn/courses/30/lessons/42577) | [PG] 42577 - 전화번호 목록 | lv1 | ✅ | ✅ | ✅ | hash |
| 3 | PG | [42578](https://programmers.co.kr/learn/courses/30/lessons/42578) | [PG] 42578 - 의상 | lv2 | ✅ | ✅ | ✅ | hash |
| 4 | PG | [42587](https://programmers.co.kr/learn/courses/30/lessons/42587) | [PG] 42587 - 프로세스 | lv2 | ❌ | ❌ | ❌ | stack,queue |
| 5 | PG | [42626](https://programmers.co.kr/learn/courses/30/lessons/42626) | [PG] 42626 - 더 맵게 | lv2 | ✅ | ✅ | ✅ | heap |
| 6 | PG | [42746](https://programmers.co.kr/learn/courses/30/lessons/42746) | [PG] 42746 - 가장 큰 수 | lv2 | ✅ | ✅ | ✅ | sort |
| 7 | PG | [42748](https://programmers.co.kr/learn/courses/30/lessons/42748) | [PG] 42748 - k번째수 | lv1 | ✅ | ✅ | ✅ | sort,array |
| 8 | PG | [42839](https://programmers.co.kr/learn/courses/30/lessons/42839) | [PG] 42839 - 소수 찾기 | lv2 | ✅ | ✅ | ✅ | search |
| 9 | PG | [42840](https://programmers.co.kr/learn/courses/30/lessons/42840) | [PG] 42840 - 모의고사 | lv1 | ✅ | ✅ | ✅ | sort |
| 10 | PG | [42860](https://programmers.co.kr/learn/courses/30/lessons/42860) | [PG] 42860 - 조이스틱 | lv2 | ✅ | ✅ | ✅ | greedy |
| 11 | PG | [42862](https://programmers.co.kr/learn/courses/30/lessons/42862) | [PG] 42862 - 체육복 | lv1 | ✅ | ✅ | ✅ | greedy |
| 12 | PG | [43165](https://programmers.co.kr/learn/courses/30/lessons/43165) | [PG] 43165 - 타겟 넘버 | lv2 | ✅ | ✅ | ✅ | dfs,bfs |
| 13 | PG | [86491](https://programmers.co.kr/learn/courses/30/lessons/86491) | [PG] 86491 - 최소직사각형 | lv1 | ✅ | ✅ | ✅ | search |

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
