package problems.pg86491;

/**
 * [PG] 86491 - 최소직사각형 https://programmers.co.kr/learn/courses/30/lessons/86491 난이도: lv1 태그: search
 *
 * <p>시간복잡도: O(n) 공간복잡도: O(1)
 */
public class Solution {

  public int solution(int[][] sizes) {
    final Wallet wallet = new Wallet();
    for (final int[] size : sizes) {
      wallet.fit(size[0], size[1]);
    }

    return wallet.area();
  }
}

class Wallet {
  private int maxLarge;
  private int maxSmall;

  Wallet() {
    this.maxLarge = 0;
    this.maxSmall = 0;
  }

  public void fit(final int w, final int h) {
    final int large = Math.max(w, h);
    final int small = Math.min(w, h);
    this.maxLarge = Math.max(large, this.maxLarge);
    this.maxSmall = Math.max(small, this.maxSmall);
  }

  public int area() {
    return this.maxLarge * this.maxSmall;
  }
}
