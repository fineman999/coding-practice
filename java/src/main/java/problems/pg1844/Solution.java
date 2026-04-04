package problems.pg1844;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * [PG] 1844 - 게임 맵 최단거리 https://programmers.co.kr/learn/courses/30/lessons/1844 난이도: lv2 태그: dfs,
 * bfs
 *
 * <p>시간복잡도: O(N*M) 공간복잡도: O(M*M)
 */
public class Solution {

  private static final List<Position> DIRECTIONS =
      List.of(new Position(-1, 0), new Position(1, 0), new Position(0, -1), new Position(0, 1));

  public int solution(int[][] maps) {
    final Deque<Position> queue = new ArrayDeque<>();
    final int n = maps.length;
    final int m = maps[0].length;
    queue.offerLast(new Position(0, 0));

    while (!queue.isEmpty()) {
      final Position current = queue.pollFirst();
      int x = current.x();
      int y = current.y();
      if (x == n - 1 && y == m - 1) {
        return maps[x][y];
      }

      for (final Position dir : DIRECTIONS) {
        int nx = x + dir.x();
        int ny = y + dir.y();

        if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
          maps[nx][ny] = maps[x][y] + 1;
          queue.offerLast(new Position(nx, ny));
        }
      }
    }
    return -1;
  }

  private record Position(int x,int y) {
  }
}
