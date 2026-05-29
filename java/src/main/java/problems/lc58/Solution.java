package problems.lc58;

/**
 * [LC] 58 - Length of Last Word
 * https://leetcode.com/problems/length-of-last-word/
 * 난이도: easy
 * 태그: array
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.trim().split(" ");
        return words[words.length -1].length();
    }
}