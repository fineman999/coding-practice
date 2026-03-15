package problems.pg42860;

/**
 * [PG] 42860 - 조이스틱 https://programmers.co.kr/learn/courses/30/lessons/42860 난이도: lv2 태그: greedy
 *
 * <p>시간복잡도: O(N^2) 공간복잡도: O(N)
 */
public class Solution {

  public int solution(String name) {
    return ArrowGenerator.calculatorUpAndDown(name) + ArrowGenerator.calculatorLeftAndRight(name);
  }

  private static class ArrowGenerator {
    private static final int alphabetA = 'A';
    private static final int recursiveAlphabetA = 'Z' + 1;

    public static int calculatorUpAndDown(final String name) {
      int count = 0;
      for (int i = 0; i < name.length(); i++) {
        count += Math.min(name.charAt(i) -alphabetA, recursiveAlphabetA - name.charAt(i));
      }
      return count;
    }

    // 3가지 구하기, 1. 그냥 차례대로, 2. 차례대로가다가 돌아가기, 3. 반대로가다가 돌아가기
    public static int calculatorLeftAndRight(final String name) {
      int n = name.length();
      int count = n - 1;
      for (int i = 0; i < n; i++) {
        int nextI = i + 1;
        while (nextI < n && name.charAt(nextI) == 'A') {
          nextI++;
        }
        int move = Math.min(count, 2 * i + (n - nextI));
        count = Math.min(move, 2 * (n - nextI) + i);
      }
      return count;
    }
  }
}
