// Package pg43162 - [PG] 43162 - 네트워크
// https://programmers.co.kr/learn/courses/30/lessons/43162
// 난이도: lv3
// 태그: DFS/BFS
//
// 시간복잡도: O(N^2)
// 공간복잡도: O(N)
package pg43162

func Solution(n int, computers [][]int) int {
	queue := make([]int, 0, n)
	pop := func() int {
		q := queue[0]
		queue = queue[1:]
		return q
	}
	push := func(x int) {
		queue = append(queue, x)
	}
	isEmpty := func() bool {
		return len(queue) == 0
	}
	visited := make([]bool, n)
	count := 0
	for i := range visited {
		if visited[i] {
			continue
		}
		visited[i] = true
		count++
		push(i)
		for !isEmpty() {
			currNode := pop()
			for nextNode, value := range computers[currNode] {
				if value == 1 && !visited[nextNode] {
					visited[nextNode] = true
					push(nextNode)
				}
			}
		}

	}
	return count
}
