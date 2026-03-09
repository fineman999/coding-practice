package problems.pg42840;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * [PG] 42840 - 모의고사 https://programmers.co.kr/learn/courses/30/lessons/42840 난이도: lv1 태그: sort
 *
 * <p>시간복잡도: O(n) 공간복잡도: O(1)
 */
public class Solution {

  private static final int[][] PATTERNS = {
    {1, 2, 3, 4, 5},
    {2, 1, 2, 3, 2, 4, 2, 5},
    {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
  };

  public int[] solution(int[] answers) {
    final int[] scores =
        Arrays.stream(PATTERNS).mapToInt(pattern -> (int)
                        IntStream.range(0, answers.length)
                                .filter(i -> pattern[i % pattern.length] == answers[i])
                                .count())
            .toArray();

    int maxScore = IntStream.of(scores).max().orElse(0);

    return IntStream.range(0, scores.length)
        .filter(i -> scores[i] == maxScore)
        .map(i -> i + 1)
        .toArray();
  }
}
