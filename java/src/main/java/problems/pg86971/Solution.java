package problems.pg86971;

import java.util.*;

/**
 * [PG] 86971 - 전력망을 둘로 나누기 https://programmers.co.kr/learn/courses/30/lessons/86971 난이도: lv2 태그:
 * search
 *
 * <p>시간복잡도: O(n^2) 공간복잡도: O(n)
 */
public class Solution {
  public int solution(int n, int[][] wires) {
    final List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (final int[] wire : wires) {
      final int v1 = wire[0];
      final int v2 = wire[1];
      graph.get(v1).add(v2);
      graph.get(v2).add(v1);
    }

    int answer = n;

    for (final int[] wire : wires) {
      int count1 = bfs(graph, wire[0], wire[1], n);
      int count2 = n - count1;

      answer = Math.min(answer, Math.abs(count1 - count2));
    }

    return answer;
  }

  private int bfs(List<List<Integer>> graph, int startNode, int ignoreNode, int n) {

    boolean[] isVisited = new boolean[n + 1];
    Queue<Integer> q = new LinkedList<>();
    q.offer(startNode);
    isVisited[startNode] = true;
    int count = 1;
    while (!q.isEmpty()) {
      final int currNode = q.poll();
      for (int neighbor : graph.get(currNode)) {
        if (!isVisited[neighbor] && neighbor != ignoreNode) {
          isVisited[neighbor] = true;
          count++;
          q.offer(neighbor);
        }
      }
    }
    return count;
  }
}
