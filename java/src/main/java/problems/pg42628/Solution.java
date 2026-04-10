package problems.pg42628;

import java.util.TreeMap;

/**
 * [PG] 42628 - 이중우선순위큐 https://programmers.co.kr/learn/courses/30/lessons/42628 난이도: lv3 태그: heap
 *
 * <p>시간복잡도: O(?) 공간복잡도: O(?)
 */
public class Solution {

  public int[] solution(String[] operations) {
    TreeMap<Node, Integer> queue = new TreeMap<>();

    int i = 0;
    for (final String operation : operations) {
      final String[] split = operation.split(" ");
      final String command = split[0];
      final int value = Integer.parseInt(split[1]);
      if (command.equals("I")) {
        queue.put(new Node(value, i), value);
        i++;
      } else if (!queue.isEmpty()) {
        if (value == 1) {
          queue.remove(queue.lastKey());
        } else {
          queue.remove(queue.firstKey());
        }
      }
    }
    if (queue.isEmpty()) {
      return new int[] {0, 0};
    }
    return new int[] {queue.lastKey().value, queue.firstKey().value};
  }

  private static class Node implements Comparable<Node>{
   final int value;
    final int index;

      private Node(final int value, final int index) {
          this.value = value;
          this.index = index;
      }

    @Override
    public int compareTo(final Node o) {
        if (this.value != o.value) {
            return Integer.compare(this.value, o.value);
        }
        return Integer.compare(this.index, o.index);
    }
  }
}
