package problems.pg42862;

import java.util.Arrays;

/**
 * [PG] 42862 - 체육복 https://programmers.co.kr/learn/courses/30/lessons/42862 난이도: lv1 태그: greedy
 *
 * <p>시간복잡도: O(n) 공간복잡도: O(n)
 */
public class Solution {

  public int solution(int n, int[] lost, int[] reserve) {
    final GymClass gymClass = new GymClass(n, lost, reserve);
    gymClass.leadUniforms();
    return gymClass.countParticipants();
  }

  public static class GymClass {
    private final int[] uniforms;

    public GymClass(final int n, final int[] lost, final int[] reverse) {
      this.uniforms = new int[n];
      for (int student : lost) uniforms[student - 1]--;
      for (int student : reverse) uniforms[student - 1]++;
    }

    void leadUniforms() {
      for (int i = 0; i < uniforms.length; i++) {
        if (uniforms[i] >= 0) continue;

        if (i > 0 && uniforms[i - 1] > 0) {
          uniforms[i]++;
          uniforms[i - 1]--;
        } else if (i + 1 < uniforms.length && uniforms[i + 1] > 0) {
          uniforms[i]++;
          uniforms[i + 1]--;
        }
      }
    }

    int countParticipants() {
      return (int) Arrays.stream(uniforms).filter(u -> u >= 0).count();
    }
  }
}
