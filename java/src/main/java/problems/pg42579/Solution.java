package problems.pg42579;

import java.util.*;

/**
 * [PG] 42579 - 베스트앨범 https://programmers.co.kr/learn/courses/30/lessons/42579 난이도: lv2 태그: hash
 *
 * <p>시간복잡도: O(NLogN) 공간복잡도: O(N)
 */
public class Solution {

  public int[] solution(String[] genres, int[] plays) {
    // 정의하기
    Map<String, Integer> genreToTotalPlays = new HashMap<>();
    Map<String, List<Song>> genreToSongs = new HashMap<>();
    for (int i = 0; i < genres.length; i++) {
      genreToTotalPlays.put(genres[i], genreToTotalPlays.getOrDefault(genres[i], 0) + plays[i]);
      genreToSongs.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(new Song(plays[i], i));
    }

    // 장르 정렬: 오름차순으로
    List<String> sortedGenres = new ArrayList<>(genreToTotalPlays.keySet());
    sortedGenres.sort((g1, g2) -> genreToTotalPlays.get(g2) - genreToTotalPlays.get(g1));
    List<Integer> answer = new ArrayList<>();
    for (String genre : sortedGenres) {
      final List<Song> songs = genreToSongs.get(genre);
      songs.sort((s1, s2) -> s1.play == s2.play ? s1.index - s2.index : s2.play - s1.play);
      for (int i = 0; i < Math.min(2, songs.size()); i++) {
        answer.add(songs.get(i).index);
      }
    }
    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

  private record Song(int play, int index) {}
}
