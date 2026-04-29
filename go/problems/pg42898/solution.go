// Package pg42898 - [PG] 42898 - 등굣길
// https://programmers.co.kr/learn/courses/30/lessons/42898
// 난이도: lv3
// 태그: DP
//
// 시간복잡도: O(NM) - N: y축 길이, M: x축 길이
// 공간복잡도: O(NM) - DP 테이블 크기
package pg42898

func Solution(m int, n int, puddles [][]int) int {
	graph := make([][]int, n)
	reminder := 1_000_000_007
	for i := range graph {
		graph[i] = make([]int, m)
	}
	for i := range puddles {
		y := puddles[i][1]
		x := puddles[i][0]
		graph[y-1][x-1] = -1
	}
	graph[0][0] = 1
	for y := 0; y < n; y++ {
		for x := 0; x < m; x++ {
			if graph[y][x] == 0 {
				up := 0
				left := 0
				if x > 0 && graph[y][x-1] != -1 {
					left = graph[y][x-1]
				}
				if y > 0 && graph[y-1][x] != -1 {
					up = graph[y-1][x]
				}
				graph[y][x] += (up + left) % reminder
			}
		}
	}
	return graph[n-1][m-1] % reminder
}
