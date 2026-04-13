// Package pg42861 - [PG] 42861 - 섬 연결하기
// https://programmers.co.kr/learn/courses/30/lessons/42861
// 난이도: lv3
// 태그: greedy
//
// 시간복잡도: O(E log E) (간선 정렬)
// 공간복잡도: O(V) (부모 배열)
package pg42861

import "sort"

func Solution(n int, costs [][]int) int {

	sort.Slice(costs, func(i, j int) bool { return costs[i][2] < costs[j][2] })
	parent := make([]int, n)
	for i := range parent {
		parent[i] = i
	}
	cost := 0
	for i := range costs {
		x, y, edge := costs[i][0], costs[i][1], costs[i][2]
		rootX := findParent(parent, x)
		rootY := findParent(parent, y)
		if rootX != rootY {
			unionParent(parent, rootX, rootY)
			cost += edge
		}
	}
	return cost
}

func findParent(parent []int, x int) int {
	if parent[x] != x {
		parent[x] = findParent(parent, parent[x])
	}
	return parent[x]
}

func unionParent(parent []int, x, y int) {
	if x < y {
		parent[x] = y
	} else {
		parent[y] = x
	}
}
