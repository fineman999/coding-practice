package problems.pg42748;

import java.util.Arrays;

/**
 * [PG] 42748 - k번째수
 * https://programmers.co.kr/learn/courses/30/lessons/42748
 * 난이도: lv1
 * 태그: sort,array
 *
 * 시간복잡도: O(M × NlogN)
 * 공간복잡도: O(N)
 */
public class Solution {

  public int[] solution(int[] array, int[][] commands) {
    int[] answer = new int[commands.length];

    for (int i = 0; i < commands.length; i++) {
      int[] sub = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
      Arrays.sort(sub);
      answer[i] = sub[commands[i][2] - 1];
    }

    return answer;
  }
}
