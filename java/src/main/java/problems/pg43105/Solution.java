package problems.pg43105;

/**
 * [PG] 43105 - 정수 삼각형 https://programmers.co.kr/learn/courses/30/lessons/43105 난이도: lv3 태그: DP
 *
 * <p>시간복잡도: O(N^2) 공간복잡도: O(1)
 */
public class Solution {
  public int solution(int[][] triangle) {
    for (int y = triangle.length - 2; y >= 0; y--) {
      for (int x = 0; x < triangle[y].length; x++) {
        if (y + 1 < triangle.length && x + 1 < triangle[y+1].length) {
          triangle[y][x] += Math.max(triangle[y+1][x+1], triangle[y+1][x]);
        }
      }
    }
    return triangle[0][0];
  }
}
