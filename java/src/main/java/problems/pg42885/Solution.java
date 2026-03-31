package problems.pg42885;

import java.util.Arrays;

/**
 * [PG] 42885 - 구명보트 https://programmers.co.kr/learn/courses/30/lessons/42885 난이도: lv2 태그: greedy
 *
 * <p>시간복잡도: O(NlogN) 공간복잡도: O(1)
 */
class Solution {
  public int solution(int[] people, int limit) {
    int answer = 0;
    Arrays.sort(people);
    int left = 0;
    int n = people.length;
    int right = n - 1;

    while (left <= right) {

      final int person = people[right];

      if (person + people[left] <= limit) {
        left++;
      }
      answer++;
      right--;
    }
    return answer;
  }
}
