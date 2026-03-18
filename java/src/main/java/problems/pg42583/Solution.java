package problems.pg42583;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

/**
 * [PG] 42583 - 다리를 지나는 트럭
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 * 난이도: lv2
 * 태그: stack,queue
 *
 * 시간복잡도: O(N)
 * 공간복잡도: O(?)
 */
public class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 1. 초기 용량을 지정하여 내부 배열 재할당 방지
        final Deque<Integer> bridge = new ArrayDeque<>(bridge_length);

        // 2. Collections.nCopies 대신 직접 0을 채움 (불변 리스트 생성 비용 제거)
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        int index = 0;
        int n = truck_weights.length;
        int totalWeight = 0;
        int time = 0;
        while (index < n && !bridge.isEmpty()) {
            final int first = bridge.pollFirst();
            totalWeight -= first;
            if (totalWeight + truck_weights[index] <=weight) {
                bridge.addLast(truck_weights[index]);
                totalWeight+= truck_weights[index];
                index++;
            } else {
                bridge.addLast(0);
            }
            time ++;
        }
        return time + bridge_length;
    }
}
