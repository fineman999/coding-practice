package problems.pg42626;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * [PG] 42626 - 더 맵게 https://programmers.co.kr/learn/courses/30/lessons/42626 난이도: lv2 태그: heap
 *
 * <p>시간복잡도: O(N log N) 공간복잡도: O(N)
 */
public class Solution {
  public int solution(int[] scoville, int K) {

    final Heap heap = new Heap(scoville);
    return heap.calculateCount(K);
  }

  public int solution02(int[] scoville, int K) {

    final ScovillFoods scovillFoods = new ScovillFoods(scoville);

    return scovillFoods.minUntilTarget(K);
  }

  // 내가 작성한 코드
  private static class Heap {
    private final PriorityQueue<Integer> priorityQueue;
    private int count;

    public Heap(int[] scoville) {
      this.priorityQueue = new PriorityQueue<>();
      Arrays.stream(scoville).forEach(priorityQueue::offer);
      count = 0;
    }

    public int calculateCount(int k) {
      while (!priorityQueue.isEmpty()) {
        if (priorityQueue.peek() >= k) {
          return count;
        }
        if (priorityQueue.size() >= 2) {
          final int firstElement = Objects.requireNonNull(priorityQueue.poll());
          final int secondElement = Objects.requireNonNull(priorityQueue.poll());
          final int calculateElement = calculateElement(firstElement, secondElement);
          priorityQueue.offer(calculateElement);
          count++;
        } else {
          return -1;
        }
      }
      return -1;
    }

    private int calculateElement(int firstElement, int secondElement) {
      return firstElement + (secondElement * 2);
    }
  }

  // 좀 더 개선한 코드

  private static class ScovillFoods {
    private final PriorityQueue<Integer> pq;

    public ScovillFoods(final int[] scoville) {
      this.pq = new PriorityQueue<>();
      for (final int s : scoville) {
        this.pq.offer(s);
      }
    }

    public int minUntilTarget(int targetScoville) {
      int mixCount = 0;

      while (pq.size() >= 2 && pq.peek() >= targetScoville) {
        int leastSpicy = pq.poll();
        int secondLeastSpicy = Objects.requireNonNull(pq.poll());

        pq.offer(mix(leastSpicy, secondLeastSpicy));
        mixCount++;
      }

      return pq.element() >= targetScoville ? mixCount : -1;
    }

    private int mix(int first, int second) {
      return first + (second * 2);
    }
  }
}
