package problems.lc27;

/**
 * [LC] 27 - Remove Element
 * https://leetcode.com/problems/remove-element/
 * 난이도: easy
 * 태그: array,two-pointers
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */

class Solution {
    public int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i  = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }
}