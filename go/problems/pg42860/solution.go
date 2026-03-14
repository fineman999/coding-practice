// Package pg42860 - [PG] 42860 - 조이스틱
// https://programmers.co.kr/learn/courses/30/lessons/42860
// 난이도: lv2
// 태그: greedy
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg42860

func Solution(name string) int {

	// 1단계 위 아래 모두 계산하기
	upAndDown := 0
	for i := range name {
		diff := int(name[i] - 'A')
		reverseDiff := int('Z' + 1 - name[i])
		if diff > reverseDiff {
			diff = reverseDiff
		}
		upAndDown += diff
	}

	// 2단계 왼쪽, 오른쪽 옮기기
	n := len(name)
	// 3단계 그냥 옆으로가기
	leftAndRight := n - 1
	for i := range name {
		nextI := i + 1
		// 4단계: A일 경우 패스
		for nextI < n && name[nextI] == 'A' {
			nextI++
		}
		// 5단계: 오른쪽 ->  + 왼쪽 <- + 뒤로
		move := i*2 + (n - nextI)
		if leftAndRight > move {
			leftAndRight = move
		}
		moveBack := 2*(n-nextI) + i
		if leftAndRight > moveBack {
			leftAndRight = moveBack
		}

	}
	return leftAndRight + upAndDown
}
