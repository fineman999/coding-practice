// Package pg86971 - [PG] 86971 - 전력망을 둘로 나누기
// https://programmers.co.kr/learn/courses/30/lessons/86971
// 난이도: lv2
// 태그: search
//
// 시간복잡도: O(n^2)
// 공간복잡도: O(n)
package pg86971

func Solution(n int, wires [][]int) int {
	graph := make(map[int][]int, n+1)
	for _, wire := range wires {
		graph[wire[0]] = append(graph[wire[0]], wire[1])
		graph[wire[1]] = append(graph[wire[1]], wire[0])
	}

	minDiff := n
	for _, wire := range wires {
		count1 := bfs(graph, wire[0], wire[1], n)
		count2 := n - count1
		diff := count1 - count2
		if diff < 0 {
			diff = -diff
		}

		if diff < minDiff {
			minDiff = diff
		}

		// 차이가 0이면 더 이상 최적값을 찾을 필요 없음
		if minDiff == 0 {
			return 0
		}
	}

	return minDiff
}

func bfs(graph map[int][]int, startNode, ignoreNode, n int) int {

	q := make([]int, n)
	head, tail := 0, 0
	size := 0
	pop := func() int {
		element := q[head]
		head = (head + 1) % n
		size--
		return element
	}

	push := func(element int) {
		q[tail] = element
		size++
		tail = (tail + 1) % n
	}

	visited := make([]bool, n+1)
	push(startNode)
	visited[startNode] = true

	count := 1
	for size > 0 {

		currNode := pop()
		for _, element := range graph[currNode] {
			if !visited[element] && element != ignoreNode {
				visited[element] = true
				count++
				push(element)
			}
		}
	}
	return count
}
