package problems.pg42895;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [PG] 42895 - N으로 표현 https://programmers.co.kr/learn/courses/30/lessons/42895 난이도: lv3 태그: DP
 *
 * <p>시간복잡도: O(4^K) 4: 사칙연산, K는 최대 8번까지 표현 가능하므로 최대 4^8 공간복잡도: O(U) U는 표현 가능한 숫자의 범위
 */
public class Solution {

  public int solution(int N, int number) {
    if (N == number) return 1;
    List<Set<Integer>> dp = new ArrayList<>();
    int k = 9; // 최대 8번까지 표현 가능하므로 9로 설정 (1~8)
    int temp = N;
    for (int i = 0; i < k; i++) {
      dp.add(new HashSet<>());
    }
    for (int i = 1; i < k; i++) {
      dp.get(i).add(temp);
      temp = temp * 10 + N;
    }

    for (int i = 1; i < k; i++) {
      for (int j = 1; j < i; j++) {
        for (final int x : dp.get(j)) {
          for (final int y : dp.get(i - j)) {
            // 사칙연산
            dp.get(i).add(x + y);
            dp.get(i).add(x - y);
            dp.get(i).add(x * y);
            if (y != 0) {
              dp.get(i).add(x / y);
            }
          }
        }
        if (dp.get(i).contains(number)) {
          return i;
        }
      }
    }

    return -1;
  }
}
