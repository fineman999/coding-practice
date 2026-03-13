package problems.pg42839;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * [PG] 42839 - 소수 찾기 https://programmers.co.kr/learn/courses/30/lessons/42839 난이도: lv2 태그: search
 *
 * <p>시간복잡도: O(?) 공간복잡도: O()
 */
public class Solution {

  public int solution(String numbers) {
    final Set<Integer> seen = new HashSet<>();
    CalculatorPrimeUtils.recursivePermutations("", numbers, seen);
    return Math.toIntExact(seen.stream().filter(CalculatorPrimeUtils::isPrime).count());
  }

  public int solution02(String numbers) {
    final Set<Integer> allCombinations = NumberGenerator.generate(numbers);

    return Math.toIntExact(allCombinations.stream().filter(PrimeValidator::isPrime).count());
  }


  private static class CalculatorPrimeUtils {

    private CalculatorPrimeUtils() {}

    public static void recursivePermutations(String current, String rest, Set<Integer> seen) {
      if (!current.isEmpty()) {
        seen.add(Integer.parseInt(current));
      }
      for (int i = 0; i < rest.length(); i++) {
        String left = rest.substring(0, i);
        String right = rest.substring(i + 1);
        recursivePermutations(
            current.concat(String.valueOf(rest.charAt(i))), left.concat(right), seen);
      }
    }

    public static boolean isPrime(int n) {
      if (n < 2) {
        return false;
      }
      for (int i = 2; i <= (int) Math.sqrt(n); i++) {
        if (n % i == 0) {
          return false;
        }
      }
      return true;
    }
  }
}

class NumberGenerator {
  public static Set<Integer> generate(String source) {
    Set<Integer> result = new HashSet<>();
    recursive("", source, result);
    return result;
  }

  private static void recursive(String current, String rest, Set<Integer> result) {
    if (!current.isEmpty()) {
      result.add(Integer.parseInt(current));
    }
    IntStream.range(0, rest.length())
        .forEach(
            i ->
                recursive(
                    current + rest.charAt(i),
                    rest.substring(0, i).concat(rest.substring(i + 1)),
                    result));
  }
}

class PrimeValidator {
  public static boolean isPrime(int n) {
    if (n < 2) return false;

    return IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
  }
}
