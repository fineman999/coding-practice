// Package pg43105 - [PG] 43105 - 정수 삼각형
// https://programmers.co.kr/learn/courses/30/lessons/43105
// 난이도: lv3
// 태그: DP
//
// 시간복잡도: O(N^2)
// 공간복잡도: O(1)
package pg43105

func Solution(triangle [][]int) int {
	for y := len(triangle) - 2; y >= 0; y-- {
		for x := 0; x < len(triangle[y]); x++ {
			maxVal := triangle[y+1][x+1]
			if maxVal < triangle[y+1][x] {
				maxVal = triangle[y+1][x]
			}
			triangle[y][x] += maxVal
		}
	}
	return triangle[0][0]
}
