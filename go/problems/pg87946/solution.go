// Package pg87946 - [PG] 87946 - 피로도
// https://programmers.co.kr/learn/courses/30/lessons/87946
// 난이도: lv2
// 태그: search
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg87946

func Solution(k int, dungeons [][]int) int {
	return recursiveMaxCount(dungeons, k, 0, 0)
}

func recursiveMaxCount(dungeons [][]int, currK, count int, isVisited int) int {

	maxCount := count
	for i := range dungeons {
		if (isVisited&(1<<i) == 0) && dungeons[i][0] <= currK {
			tempCount := recursiveMaxCount(dungeons, currK-dungeons[i][1], count+1, isVisited|1<<i)
			if tempCount > maxCount {
				maxCount = tempCount
			}
		}
	}
	return maxCount
}
