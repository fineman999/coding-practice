// Package pg42577 - [PG] 42577 - 전화번호 목록
// https://programmers.co.kr/learn/courses/30/lessons/42577
// 난이도: lv1
// 태그: hash
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg42577

import (
	"sort"
	"strings"
)

func Solution(phoneBook []string) bool {
	sort.Strings(phoneBook)
	for i := range phoneBook {
		if i+1 == len(phoneBook) {
			continue
		}
		if strings.HasPrefix(phoneBook[i+1], phoneBook[i]) {
			return false
		}
	}
	return true
}
