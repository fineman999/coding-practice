package problems.lc13;

import java.util.HashMap;
import java.util.Map;

/**
 * [LC] 13 - Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 * 난이도: easy
 * 태그: array
 *
 * LeetCode 제출용 시그니처를 직접 작성하세요.
 */
class Solution {
    private static final Map<Character, Integer> dict = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );
    public int romanToInt(String s) {
        int answer = 0;
        for (int i = 0; i<s.length(); i++) {
            if (s.length() > i+1 && dict.get(s.charAt(i)) < dict.get(s.charAt(i+1))) {
                answer -= dict.get(s.charAt(i));
            }else{
                answer += dict.get(s.charAt(i));
            }
        }
        return answer;
    }
}