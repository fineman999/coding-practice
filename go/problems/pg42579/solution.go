// Package pg42579 - [PG] 42579 - 베스트앨범
// https://programmers.co.kr/learn/courses/30/lessons/42579
// 난이도: lv2
// 태그: hash
//
// 시간복잡도: O(NLogN)
// 공간복잡도: O(N)
package pg42579

import "sort"

type song struct {
	play  int
	index int
}
type genre struct {
	name string
	play int
}

func Solution(genres []string, plays []int) []int {
	genresToSum := make(map[string]int)
	genresToSongs := make(map[string][]song)
	for i, play := range plays {
		genresToSum[genres[i]] += play
		genresToSongs[genres[i]] = append(genresToSongs[genres[i]], song{play: play, index: i})
	}

	// 정렬
	sortedGenres := make([]genre, 0, len(genresToSum))
	for key, value := range genresToSum {
		sortedGenres = append(sortedGenres, genre{name: key, play: value})
	}
	sort.Slice(sortedGenres, func(i, j int) bool {
		return sortedGenres[j].play < sortedGenres[i].play
	})

	var answers []int
	// 조회
	for _, element := range sortedGenres {
		songs := genresToSongs[element.name]
		sort.Slice(songs, func(i, j int) bool {
			if songs[i].play != songs[j].play {
				return songs[j].play < songs[i].play
			}
			if songs[i].index != songs[j].index {
				return songs[i].index < songs[j].index
			}
			return true
		})
		limit := 2
		if len(songs) < 2 {
			limit = len(songs)
		}
		for _, s := range songs[:limit] {
			answers = append(answers, s.index)
		}
	}

	return answers
}
