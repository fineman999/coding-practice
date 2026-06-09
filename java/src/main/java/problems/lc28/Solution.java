package problems.lc28;

/**
 * [LC] 28 - Find the Index of the First Occurrence in a String
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 난이도: easy
 * 태그: array
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }

        // 1부: LPS 배열 만들기 (패턴의 특징 분석)
        final int[] lpsArray = computeLPSArray(needle);

        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = lpsArray[j -1];
            }
            if (j == needle.length()) {
                return i - j;
            }
        }
        return -1;
    }

    private int[] computeLPSArray(final String needle) {
        int m = needle.length();
        int []lps = new int[m];
        int prevLPS = 0;
        int i = 1;
        while (i < m) {
            // 문자가 일치하면 일치 길이를 1 늘리고 lps 배열에 기록
            if(needle.charAt(i) == needle.charAt(prevLPS)) {
                lps[i] = prevLPS + 1;
                prevLPS++;
                i++;
            }else if (prevLPS == 0) {
                lps[i] = 0;
                i++;
            } else {
                prevLPS = lps[prevLPS - 1];
            }
        }
        return lps;

    }
}