package problems.pg42747;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * [PG] 42747 - H-index
 * https://programmers.co.kr/learn/courses/30/lessons/42747
 * 난이도: lv2
 * 태그: sort
 *
 * 시간복잡도: O(N Log N)
 * 공간복잡도: O(N)
 */
public class Solution {

    public int solution(int[] citations) {
        int[] sortedCitations = getSortReverse(citations);

        return IntStream.range(0, sortedCitations.length)
                .map(i -> Math.min(i + 1, sortedCitations[i]))
                .max()
                .orElse(0);
    }

    private int[] getSortReverse(final int[] citations) {
        return Arrays.stream(citations)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
