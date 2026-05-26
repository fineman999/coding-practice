package problems.lc169;

/**
 * [LC] 169 - Majority Element
 * https://leetcode.com/problems/majority-element/
 * 난이도: easy
 * 태그: array
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */

class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int answer = 0;
        for (final int num : nums) {
            if (count == 0) {
                answer = num;
            }
            if (answer == num) {
                count++;
            } else {
                count--;
            }
        }
        return answer;
    }
}