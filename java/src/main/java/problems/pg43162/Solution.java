package problems.pg43162;

import java.util.*;

/**
 * [PG] 43162 - 네트워크 https://programmers.co.kr/learn/courses/30/lessons/43162 난이도: lv3 태그: DFS/BFS
 *
 * <p>시간복잡도: O(N^2) 공간복잡도: O(N^2)
 */
public class Solution {

  public int solution(int n, int[][] computers) {

    // 1. 초기화
    boolean[] visited = new boolean[n];
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < computers.length; i++) {
      for (int j = i + 1; j < computers[i].length; j++) {
        if (computers[i][j] == 1) {
          graph.get(i).add(j);
          graph.get(j).add(i);
        }
      }
    }
    // BFS 실행
    Queue<Integer> queue = new ArrayDeque<>();
    int countNode = 0;
    int answer = 0;

    while (countNode < n) {
      for (int i = 0; i < visited.length; i++) {
        if (!visited[i]) {
          visited[i] = true;
          queue.offer(i);
          countNode++;
          answer++;
          break;
        }
      }
      while (!queue.isEmpty()) {
        int curr = queue.poll();
        for (final int next : graph.get(curr)) {
          if (!visited[next]) {
            visited[next] = true;
            queue.offer(next);
            countNode++;
          }
        }
      }

    }
    return answer;
  }
}
