// Package pg1844 - [PG] 1844 - 게임 맵 최단거리
// https://programmers.co.kr/learn/courses/30/lessons/1844
// 난이도: lv2
// 태그: dfs, bfs
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg1844

import "errors"

var DX = []int{1, 0, -1, 0}
var DY = []int{0, 1, 0, -1}

func Solution(maps [][]int) int {
	n, m := len(maps), len(maps[0])
	queue := make([][]int, 0)
	offerLast := func(element []int) {
		queue = append(queue, element)
	}
	isEmpty := func() bool {
		if len(queue) > 0 {
			return false
		}
		return true
	}
	popFirst := func() ([]int, error) {
		if isEmpty() {
			return nil, errors.New("queue is empty")
		}
		first := queue[0]
		queue = queue[1:]
		return first, nil
	}

	// init
	offerLast([]int{0, 0})

	for !isEmpty() {
		element, err := popFirst()
		if err != nil {
			return -1
		}
		x, y := element[0], element[1]
		if x == n-1 && y == m-1 {
			return maps[n-1][m-1]
		}
		for i := range 4 {
			nx, ny := x+DX[i], y+DY[i]
			if 0 <= nx && nx < n && 0 <= ny && ny < m && maps[nx][ny] == 1 {
				offerLast([]int{nx, ny})
				maps[nx][ny] = maps[x][y] + 1
			}
		}
	}
	return -1
}
