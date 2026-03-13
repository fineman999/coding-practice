// Package pg42839 - [PG] 42839 - 소수 찾기
// https://programmers.co.kr/learn/courses/30/lessons/42839
// 난이도: lv2
// 태그: search
//
// 시간복잡도: O(K x M^1/2)
// 공간복잡도: O(K)
package pg42839

import (
	"math"
	"strconv"
)

func Solution(numbers string) int {
	setTable := make(map[int]struct{})
	permutations("", numbers, setTable)

	count := 0
	for v := range setTable {
		if isPrime(v) {
			count++
		}
	}
	return count
}

func isPrime(n int) bool {
	if n < 2 {
		return false
	}
	sqrtN := int(math.Sqrt(float64(n)))
	//for i := range sqrtN { -> 정수형 사용 Go 1.22 버전 이후부터 사용 가능
	for i := 2; i <= sqrtN; i++ {
		if n%i == 0 {
			return false
		}
	}
	return true
}

func permutations(current string, rest string, seen map[int]struct{}) {
	if current != "" {
		if atoi, err := strconv.Atoi(current); err == nil {
			seen[atoi] = struct{}{}
		}
	}
	for i := 0; i < len(rest); i++ {
		left := rest[:i]
		right := rest[i+1:]
		nextArray := left + right
		next := current + string(rest[i])
		permutations(next, nextArray, seen)
	}
}
