package problems.pg42578;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * [PG] 42578 - 의상 https://programmers.co.kr/learn/courses/30/lessons/42578 난이도: lv2 태그: hash
 *
 * <p>시간복잡도: O(n) 공간복잡도: O(n)
 */
public class Solution {

  public int solution(String[][] clothes) {
    final Closet closet = new Closet(clothes);
    return closet.getCount();
  }

  private static class Closet {
    private final Map<String, Long> closet;

    public Closet(final String[][] clothes) {

      this.closet =
          Arrays.stream(clothes)
              .collect(Collectors.groupingBy(cloth -> cloth[1], Collectors.counting()));
    }

    public int getCount() {
      //      reduce(초기값, (누적값, 현재값) -> 계산식)
      return Math.toIntExact(closet.values().stream().map(i -> i + 1).reduce(1L, (a, b) -> (a * b)) - 1);
    }
  }
}
