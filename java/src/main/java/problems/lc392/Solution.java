package problems.lc392;

/**
 * [LC] 392 - Is Subsequence https://leetcode.com/problems/is-subsequence/ 난이도: easy 태그: Two
 * Pointers
 *
 * <p>LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
  public boolean isSubsequence(String s, String t) {
    int sPointer = 0;
    for (int i = 0; i < t.length(); i++) {
        if (sPointer < s.length() && s.charAt(sPointer) == t.charAt(i)) {
          sPointer++;
        }
    }
      return sPointer == s.length();
  }
}
