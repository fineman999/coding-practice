// Package pg42840 - [PG] 42840 - 모의고사
// https://programmers.co.kr/learn/courses/30/lessons/42840
// 난이도: lv1
// 태그: sort
//
// 시간복잡도: O(n) 반복문 하나
// 공간복잡도: O(1) 고정값이기 때문
// 정리하면 스택에 가려면 세 가지 조건이 다 만족되어야 합니다:
//
// 함수 밖으로 안 나감 (포인터 반환, 클로저 캡처 등 없음)
// 컴파일 타임에 크기를 알 수 있음
// 크기가 합리적임
package pg42840

var patterns = [3][]int{
	{1, 2, 3, 4, 5},
	{2, 1, 2, 3, 2, 4, 2, 5},
	{3, 3, 1, 1, 2, 2, 4, 4, 5, 5},
}

func Solution(answers []int) []int {
	var scores [3]int
	for i, answer := range answers {
		for p := range patterns {
			if patterns[p][i%len(patterns[p])] == answer {
				scores[p]++
			}
		}
	}
	maxScore := max(scores[0], scores[1], scores[2])

	result := make([]int, 0, 3)
	for i, s := range scores {
		if s == maxScore {

			result = append(result, i+1)
		}
	}
	return result
}
