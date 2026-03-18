// Package pg42583 - [PG] 42583 - 다리를 지나는 트럭
// https://programmers.co.kr/learn/courses/30/lessons/42583
// 난이도: lv2
// 태그: stack,queue
//
// 시간복잡도: O(N) -> 트럭 개수
// 공간복잡도: O(L) -> 다리 길이
package pg42583

func Solution(bridgeLength int, weight int, truckWeights []int) int {
	// 브릿지 초기화
	bridge := make([]int, bridgeLength)
	head, tail := 0, 0
	pop := func() int {
		d := bridge[head]
		bridge[head] = 0
		head = (head + 1) % bridgeLength
		return d
	}
	push := func(d int) {
		bridge[tail] = d
		tail = (tail + 1) % bridgeLength
	}
	time, bridgeWeight, truckIndex := 0, 0, 0
	for truckIndex < len(truckWeights) {
		first := pop()
		bridgeWeight -= first
		if bridgeWeight+truckWeights[truckIndex] <= weight {
			push(truckWeights[truckIndex])
			bridgeWeight += truckWeights[truckIndex]
			truckIndex++
		} else {
			push(0)
		}
		time++
	}
	return time + bridgeLength
}
