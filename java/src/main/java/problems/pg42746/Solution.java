package problems.pg42746;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * [PG] 42746 - 가장 큰 수 https://programmers.co.kr/learn/courses/30/lessons/42746 난이도: lv2 태그: sort
 *
 * <p>시간복잡도: O(?) 공간복잡도: O(?)
 */
public class Solution {

  public String solution(int[] numbers) {
    return solution02(numbers);
  }

  private static String solution02(final int[] numbers) {
    final String[] array = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);

    Arrays.sort(array, (a, b) -> (b + a).compareTo(a + b));
    if (array[0].equals("0")) {
      return "0";
    }
    return String.join("", array);
  }

  private String solution01(final int[] numbers) {
    final String[] array =
        Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .sorted(Comparator.comparing((String a) -> a + a + a + a).reversed())
            .toArray(String[]::new);

    if (array[0].equals("0")) {
      return "0";
    }
    return String.join("", array);
  }
}
