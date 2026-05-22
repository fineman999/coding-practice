package problems.lc26;

/**
 * [LC] 26 - Remove Duplicates
 * https://leetcode.com/problems/remove-duplicates/
 * 난이도: easy
 * 태그: array,two-pointers
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[right] != nums[i]) {
                nums[++right] = nums[i];
            }
        }
        return right + 1;
    }
}