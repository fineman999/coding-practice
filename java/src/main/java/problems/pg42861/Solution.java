package problems.pg42861;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [PG] 42861 - 섬 연결하기 https://programmers.co.kr/learn/courses/30/lessons/42861 난이도: lv3 태그: greedy
 *
 * <p>시간복잡도: O(E Log E) 공간복잡도: O(N) - 부모 배열
 */
public class Solution {

  public int solution(int n, int[][] costs) {

    Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

    int[] parents = new int[n];
    for (int i = 0; i < n; i++) {
      parents[i] = i;
    }
    int answer = 0;
    int totalCount = 0;
    for (final int[] cost : costs) {
      int parentX = findParent(parents, cost[0]);
      int parentY = findParent(parents, cost[1]);
      int edge = cost[2];
      if (parentX != parentY) {
        unionParent(parents, parentX, parentY);
        answer += edge;
        totalCount += 1;
        if (totalCount == n-1) break;
      }
    }
    return answer;
  }

  private int findParent(int[] parents, int index) {
    if (parents[index] != index) {
      parents[index] = findParent(parents, parents[index]);
    }
    return parents[index];
  }

  private void unionParent(int[] parents, int parentX, int parentY) {
    if (parentX < parentY) {
      parents[parentY] = parentX;
    } else {
      parents[parentX] = parentY;
    }
  }
}
