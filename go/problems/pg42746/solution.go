// Package pg42746 - [PG] 42746 - 가장 큰 수
// https://programmers.co.kr/learn/courses/30/lessons/42746
// 난이도: lv2
// 태그: sort
//
// 시간복잡도: O()
// 공간복잡도: O(?)
package pg42746

import (
	"sort"
	"strconv"
	"strings"
)

func Solution(numbers []int) string {
	return solution02(numbers)
}

func solution02(numbers []int) string {
	numbersToString := make([]string, len(numbers))
	for i := range numbers {
		numbersToString[i] = strconv.Itoa(numbers[i])
	}
	sort.Slice(numbersToString, func(i, j int) bool {
		return numbersToString[i]+numbersToString[j] > numbersToString[j]+numbersToString[i]
	})
	if numbersToString[0] == "0" {
		return "0"
	}
	return strings.Join(numbersToString, "")
}

// 내가 작성한거
func solution01(numbers []int) string {
	// 1. []int → []string 변환
	strNumbers := make([]string, len(numbers))
	for i, number := range numbers {
		strNumbers[i] = strconv.Itoa(number)
	}

	// 2. a+b > b+a 기준으로 내림차순 정렬
	sortBigNumber := NewSortBigNumber(strNumbers, func(a, b string) bool {
		return a+b > b+a
	})

	// 3. 정렬된 결과를 하나의 문자열로 합치기
	result := strings.Join(sortBigNumber.Get(), "")

	// 4. 엣지 케이스: "000..." → "0"
	// (int 변환 없이 첫 문자만 확인 → 큰 입력에서도 안전)
	if result[0] == '0' {
		return "0"
	}
	return result
}

type SortBigNumberInterface[T any] interface {
	Get() []T
}

type SortBigNumber[T any] struct {
	internal *internalSortBigNumber[T]
}

func NewSortBigNumber[T any](data []T, less func(a, b T) bool) *SortBigNumber[T] {
	ih := &internalSortBigNumber[T]{
		data: data,
		less: less,
	}
	sort.Sort(ih)
	return &SortBigNumber[T]{internal: ih}
}

func (s *SortBigNumber[T]) Get() []T {
	return s.internal.data
}

type internalSortBigNumber[T any] struct {
	data []T
	less func(a, b T) bool
}

func (s *internalSortBigNumber[T]) Len() int {
	return len(s.data)
}

func (s *internalSortBigNumber[T]) Less(i, j int) bool {
	return s.less(s.data[i], s.data[j])
}

func (s *internalSortBigNumber[T]) Swap(i, j int) {
	s.data[i], s.data[j] = s.data[j], s.data[i]
}

var _ SortBigNumberInterface[int] = (*SortBigNumber[int])(nil)
