// Package pg42747 - [PG] 42747 - H-index
// https://programmers.co.kr/learn/courses/30/lessons/42747
// 난이도: lv2
// 태그: sort
//
// 시간복잡도: O(n log n)
// 공간복잡도: O(n)
package pg42747

import (
	"sort"
)

func Solution(citations []int) int {
	sort.Sort(sort.Reverse(sort.IntSlice(citations)))
	// 최신 Go 방식: 내림차순 정렬
	//slices.SortFunc(citations, func(a, b int) int {
	//	return cmp.Compare(b, a)
	//})
	for i := 0; i < len(citations); i++ {
		if i >= citations[i] {
			return i
		}
	}
	return len(citations)
}
