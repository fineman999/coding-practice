package problems.pg84512;

/**
 * [PG] 84512 - 모음사진
 * https://programmers.co.kr/learn/courses/30/lessons/84512
 * 난이도: lv2
 * 태그: search
 *
 * 시간복잡도: O(5^n)
 * 공간복잡도: O(n)
 */
public class Solution {

    public int solution(String words) {
        final WordCountGenerator generator = new WordCountGenerator(5, words, new String[]{"A", "E", "I", "O", "U"});
        return generator.calculatorCount();
    }

    private static class WordCountGenerator {
        private final int n;
        private final String words;
        private final String[] alpha;
        private int count;
        private final StringBuilder sb;

        private WordCountGenerator(final int n, final String words, final String[] alpha) {
            this.n = n;
            this.words = words;
            this.alpha = alpha;
            sb = new StringBuilder();
            this.count = 0;
        }

        private int calculatorCount() {
            count =0;
            if (recursive(sb)) {
                return count;
            }
            return 0;
        }

        private boolean recursive(StringBuilder currWord){
            if (sb.toString().equals(words)) {
                return true;
            }
            if (currWord.length() == n) {
                return false;
            }
            for (final String temp : alpha) {
                count++;
                sb.append(temp);
                if (recursive(sb)) {
                    return true;
                }
                sb.deleteCharAt(sb.length()-1);
            }
            return false;
        }
    }
}
