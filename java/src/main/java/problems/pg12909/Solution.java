package problems.pg12909;

/**
 * [PG] 12909 - 올바른 괄호 https://programmers.co.kr/learn/courses/30/lessons/12909 난이도: lv2 태그:
 * stack,queue
 *
 * <p>시간복잡도: O(n) 공간복잡도: O(1)
 */
public class Solution {

  public boolean solution(String s) {

    int count = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        count++;
      } else {
        if (count == 0) {
          return false;
        }
        count--;
      }
    }
    return count == 0;
  }

  public boolean solution02(String s) {
    final BracketValidator bracketValidator = new BracketValidator();

    for (final char c : s.toCharArray()) {
      if (!bracketValidator.process(c)) {
        return false;
      }
    }
    return bracketValidator.isValid();
  }

  private static class BracketValidator {
    private static final char OPEN_BRACKET = '(';
    private int openCount = 0;

    public boolean process(char c) {
      if (c == OPEN_BRACKET) {
        openCount++;
      } else {
        openCount--;
      }
      // 음수가 되면 무조건 False
      return openCount >= 0;
    }

    public boolean isValid() {
      return openCount == 0;
    }
  }
}
