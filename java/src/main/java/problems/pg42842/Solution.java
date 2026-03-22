package problems.pg42842;

/**
 * [PG] 42842 - 완전탐색 https://programmers.co.kr/learn/courses/30/lessons/42842 난이도: lv2 태그: search
 *
 * <p>시간복잡도: O(sqrt(n)) 공간복잡도: O(1)
 */
public class Solution {

  public int[] solution(int brown, int yellow) {
    int maxNumber = brown + yellow;
    // h는 최소 3이상
    for (int h = 3; h < Math.sqrt(maxNumber)+1; h++) {
      if (isFactor(maxNumber, h)) {
        final Carpet carpet = new Carpet(maxNumber / h, h);
        if (carpet.matches(yellow)) {
          return carpet.toArray();
        }
      }
    }
    return new int[] {0, 0};
  }

  private boolean isFactor(int number, int element) {
    return number % element == 0;
  }

  private record Carpet(int width,int height) {

    public boolean matches(int yellow) {
        return (width - 2) * (height - 2) == yellow;
      }

      public int[] toArray() {
        return new int[] {width, height};
      }
    }
}
