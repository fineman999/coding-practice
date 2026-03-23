package problems.pg87946;

import java.util.Arrays;

/**
 * [PG] 87946 - 피로도
 * https://programmers.co.kr/learn/courses/30/lessons/87946
 * 난이도: lv2
 * 태그: search
 *
 * 시간복잡도: O(N!)
 * 공간복잡도: O(N)
 */
public class Solution {

    public int solution(int k, int[][] dungeons) {

        return recursiveMaxCount(dungeons, 0, k,0);
    }

    private int recursiveMaxCount(int[][] dungeons, int isVisited, int currentK, int count) {

        int maxCount = count;

        for (int i = 0; i < dungeons.length; i++) {
            if ((isVisited & (1 << i))== 0 && dungeons[i][0] <= currentK) {
                maxCount = Math.max(recursiveMaxCount(dungeons, isVisited | 1 << i,currentK-dungeons[i][1],count+1), maxCount);
            }
        }
        return maxCount;
    }
}
