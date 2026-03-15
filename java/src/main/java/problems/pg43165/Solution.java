package problems.pg43165;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [PG] 43165 - 타겟 넘버 https://programmers.co.kr/learn/courses/30/lessons/43165 난이도: lv2 태그: dfs,bfs
 *
 * <p>시간복잡도: O(2^N) 공간복잡도: O(N)
 * +,-를 N번만큼 배로 증가하므로 시간복잡도는 2^N
 */
public class Solution {

  public int solution(int[] numbers, int target) {
    return dfs(numbers, target, 0, 0);
  }

  public int solution01(int[] numbers, int target) {
    final ArrayDeque<Integer> restDeque = new ArrayDeque<>();
    final ArrayDeque<Integer> tempDeque = new ArrayDeque<>();
    for (final int number : numbers) {
      if (restDeque.isEmpty()) {
        restDeque.add(number);
        restDeque.add(-number);
      } else {
        while (!restDeque.isEmpty()) {
          final int element = restDeque.pollFirst();
          tempDeque.add(element + number);
          tempDeque.add(element - number);
        }
        while (!tempDeque.isEmpty()) {
          restDeque.add(tempDeque.pollFirst());
        }
      }
    }
    return restDeque.stream()
        .filter(element -> target == element)
        .collect(Collectors.collectingAndThen(Collectors.counting(), Long::intValue));
  }
  public int solution02(int[] numbers, int target) {
    // 결과를 담을 리스트 (매 단계마다 갱신됨)
    List<Integer> currentSums = new ArrayList<>();
    currentSums.add(0);

    for (int number : numbers) {
      List<Integer> nextSums = new ArrayList<>();
      for (int sum : currentSums) {
        nextSums.add(sum + number);
        nextSums.add(sum - number);
      }
      // 참조만 교체하여 temp에서 복사하는 비용 제거
      currentSums = nextSums;
    }

    // 마지막에 target과 일치하는 개수 카운트
    int count = 0;
    for (int sum : currentSums) {
      if (sum == target) count++;
    }
    return count;
  }

  private int dfs(int[] numbers, int target, int index, int sum) {
    // 모든 숫자를 다 사용했을 때
    if (index == numbers.length) {
      return sum == target ? 1 : 0;
    }

    // 현재 숫자를 더하는 경우 + 빼는 경우의 합계를 반환
    return dfs(numbers, target, index + 1, sum + numbers[index])
            + dfs(numbers, target, index + 1, sum - numbers[index]);
  }

}
