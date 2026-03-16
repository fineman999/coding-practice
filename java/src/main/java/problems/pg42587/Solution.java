package problems.pg42587;

import java.util.*;

/**
 * [PG] 42587 - 프로세스 https://programmers.co.kr/learn/courses/30/lessons/42587 난이도: lv2 태그:
 * stack,queue
 *
 * <p>시간복잡도: O(N^2) 공간복잡도: O(N)
 */
public class Solution {

  public int solution(int[] priorities, int location) {

    final Queue<Process> queue = new ArrayDeque<>();
    for (int i = 0; i < priorities.length; i++) {
      queue.offer(new Process(i, priorities[i]));
    }
    priorities =
        Arrays.stream(priorities)
            .boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int count = 0;
    while (!queue.isEmpty()) {
      final Process process = queue.poll();
      if (process.priority() < priorities[count]) {
        queue.offer(process);
      } else {
        count++;
        if (process.idx() == location) {
          return count;
        }
      }
    }

    return count;
  }

  private record Process(int idx, int priority) {}
}
