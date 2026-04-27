// Package pg42895 - [PG] 42895 - N으로 표현
// https://programmers.co.kr/learn/courses/30/lessons/42895
// 난이도: lv3
// 태그: DP
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg42895

func Solution(N int, number int) int {
	if N == number {
		return 1
	}

	// 각 사용 횟수(1~8)별로 만들 수 있는 숫자를 저장할 Set(map 이용)
	sets := make([]map[int]bool, 9) // 0번 인덱스는 사용하지 않음
	for i := 0; i < 9; i++ {
		sets[i] = make(map[int]bool)
	}
	// 1. 초기화: N, NN, NNN... 채우기
	temp := N
	for i := 1; i < 9; i++ {
		sets[i][temp] = true
		temp = temp*10 + N
	}
	// 2. DP 수행
	for i := 1; i < 9; i++ {
		for j := 1; j < i; j++ {
			// i번 사용해서 만든 수는 (j번 사용) [연산]
			for a := range sets[j] {
				for b := range sets[i-j] {
					sets[i][a+b] = true
					sets[i][a-b] = true
					sets[i][a*b] = true
					if b != 0 {
						sets[i][a/b] = true
					}
				}
			}
		}
		if sets[i][number] {
			return i
		}
	}

	return -1
}
