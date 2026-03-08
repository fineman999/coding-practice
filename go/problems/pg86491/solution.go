// Package pg86491 - [PG] 86491 - 최소직사각형
// https://programmers.co.kr/learn/courses/30/lessons/86491
// 난이도: lv1
// 태그: search
//
// 시간복잡도: O(n) -> 슬라이스를 한 번만 순회
// 공간복잡도: O(1) -> 변수만 사용하기 때문
package pg86491

func Solution(sizes [][]int) int {
	maxLarge, maxSmall := 0, 0
	for _, size := range sizes {
		w, h := size[0], size[1]
		// 큰 쪽과 작은 쪽을 정규화
		if w < h {
			w, h = h, w
		}
		maxLarge = max(maxLarge, w)
		maxSmall = max(maxSmall, h)
	}
	return maxSmall * maxLarge
}
