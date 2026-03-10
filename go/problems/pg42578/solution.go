// Package pg42578 - [PG] 42578 - 의상
// https://programmers.co.kr/learn/courses/30/lessons/42578
// 난이도: lv2
// 태그: hash
//
// 시간복잡도: O(n)
// 공간복잡도: O(n)
package pg42578

func Solution(clothes [][]string) int {
	// 최종 크기를 예상할 . 있다면, 반드시 초기 용량을 설정- 메모리 할당 비용이 크게 감소
	wardrobe := make(map[string]int, len(clothes))
	// key, value로 정리
	for i := range clothes {
		key := clothes[i][1]
		wardrobe[key]++
	}
	result := 1
	for _, value := range wardrobe {
		result *= value + 1
	}

	return result - 1
}
