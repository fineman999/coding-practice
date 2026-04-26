// Package pg42884 - [PG] 42884 - 단속카메라
// https://programmers.co.kr/learn/courses/30/lessons/42884
// 난이도: lv3
// 태그: greedy
//
// 시간복잡도: O(n log n) (정렬)
// 공간복잡도: O(n) (정렬로 인해 추가 공간이 필요할 수 있음)
package pg42884

import "sort"

func Solution(routes [][]int) int {

	sort.Slice(routes, func(i, j int) bool {
		return routes[i][1] < routes[j][1]
	})

	answer := 1
	lastCameraPosition := routes[0][1]
	for i := 1; i < len(routes); i++ {
		route := routes[i]
		if route[0] > lastCameraPosition {
			answer++
			lastCameraPosition = route[1]
		}
	}

	return answer
}
