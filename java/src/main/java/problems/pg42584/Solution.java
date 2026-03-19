package problems.pg42584;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [PG] 42584 - 주식가격
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 * 난이도: lv2
 * 태그: stack,queue
 *
 * 시간복잡도: O(n)
 * 공간복잡도: O(n)
 */
public class Solution {

    public int[] solution(int[] prices) {
        int n = prices.length;
        // 초기 용량을 설정하여 내부 배열 재할당 방지
        Deque<Integer> stack = new ArrayDeque<>(n);
        int[] answer = new int[n];

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peekLast()] > prices[i]) {
                final int pollLast = stack.pollLast();
                answer[pollLast] = i - pollLast;
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            final int pollLast = stack.pollLast();
            answer[pollLast] = n - 1 - pollLast;
        }
        return answer;
    }
}
