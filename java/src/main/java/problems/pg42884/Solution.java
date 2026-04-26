package problems.pg42884;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [PG] 42884 - 단속카메라
 * https://programmers.co.kr/learn/courses/30/lessons/42884
 * 난이도: lv3
 * 태그: greedy
 *
 * 시간복잡도: O(N Log N)
 * 공간복잡도: O(N)
 */
public class Solution {

    public int solution(int[][] routes) {

    Arrays.sort(routes, Comparator.comparing(a -> a[1]));

    int lastCameraPosition = routes[0][1];
    int answer = 1;
    for (int i  = 1; i < routes.length; i++) {

        if (routes[i][0] > lastCameraPosition)  {
            lastCameraPosition = routes[i][1];
            answer++;
        }
    }
        return answer;
    }
}
