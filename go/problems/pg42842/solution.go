// Package pg42842 - [PG] 42842 - 완전탐색
// https://programmers.co.kr/learn/courses/30/lessons/42842
// 난이도: lv2
// 태그: search
//
// 시간복잡도: O(sqrt(n))
// 공간복잡도: O(1)
package pg42842

func Solution(brown int, yellow int) []int {
	totalArea := brown + yellow
	for h := 3; h*h <= totalArea; h++ {
		if totalArea%h == 0 {
			w := totalArea / h
			if (w-2)*(h-2) == yellow {
				return []int{w, h}
			}
		}
	}
	return nil
}
