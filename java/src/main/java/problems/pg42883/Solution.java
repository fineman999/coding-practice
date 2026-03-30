package problems.pg42883;

import java.util.ArrayDeque;

/**
 * [PG] 42883 - 큰 수 만들기 https://programmers.co.kr/learn/courses/30/lessons/42883 난이도: lv2 태그: greedy
 *
 * <p>시간복잡도: O(n) 공간복잡도: O(n)
 */
public class Solution {

  public String solution(String number, int k) {
    final ArrayDeque<Character> stack = new ArrayDeque<>();
    int restK = k;
    for (int i = 0; i < number.length(); i++) {
      final char element = number.charAt(i);
      while (!stack.isEmpty() && stack.peekLast() < element && restK > 0) {
        stack.pollLast();
        restK--;
      }
      stack.offerLast(element);
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < number.length() - k; i++) {
      sb.append(stack.pollFirst());
    }
    return sb.toString();
  }
}
