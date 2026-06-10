package problems.lc205;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [LC] 205 - Isomorphic Strings
 * https://leetcode.com/problems/isomorphic-strings/
 * 난이도: easy
 * 태그: Hashmap
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        final Set<Character> usedValues = new HashSet<>();
        final Map<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i< s.length(); i++) {
            if (!hashMap.containsKey(s.charAt(i))) {
                if (usedValues.contains(t.charAt(i))) {
                    return false;
                }
                hashMap.put(s.charAt(i), t.charAt(i));
                usedValues.add(t.charAt(i));
            }
            if(hashMap.get(s.charAt(i)) != t.charAt(i)){
                return false;
            }
        }
        return true;
    }
}