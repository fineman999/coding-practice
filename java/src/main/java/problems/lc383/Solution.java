package problems.lc383;

import java.util.HashMap;
import java.util.Map;

/**
 * [LC] 383 - Ransom Note
 * https://leetcode.com/problems/ransom-note/
 * 난이도: easy
 * 태그: Hashmap
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        final Map<Character, Integer> hashMap = new HashMap<>();
        for (int i =0; i< magazine.length(); i++) {
            hashMap.put(magazine.charAt(i), hashMap.getOrDefault(magazine.charAt(i), 0)+1);
        }
        for (int i =0; i< ransomNote.length(); i++) {
            int check = hashMap.getOrDefault(ransomNote.charAt(i), 0);
            if (check == 0) {
                return false;
            }
            hashMap.put(ransomNote.charAt(i), check - 1);
        }
        return true;
    }
}