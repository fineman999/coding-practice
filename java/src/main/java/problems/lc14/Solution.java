package problems.lc14;

/**
 * [LC] 14 - Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 * 난이도: easy
 * 태그: array
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0].isEmpty()) {
            return "";
        }
        String answer = "";
        for(int i =0; i< strs[0].length(); i++) {
            final String tempAnswer = strs[0].substring(0, i);
            boolean valid = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() > i && !tempAnswer.equals(strs[j].substring(0, i))) {
                    valid = false;
                    break;
                }
                if (strs[j].length() <= i) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                break;
            }
            answer = tempAnswer;
        }
        return answer;
    }
}