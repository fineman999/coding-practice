package problems.pg86491;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 86491 - 최소직사각형")
class SolutionTest {

  private final Solution solution = new Solution();

  // 1. record 안에 toString()을 재정의해서 예쁘게 꾸밉니다!
  record TestCase(int[][] sizes, int expected) {
    @Override
    public String toString() {
      // Arrays.deepToString()이 2차원 배열을 예쁜 괄호 문자열로 바꿔줍니다.
      return "기대 정답: " + expected + ", 배열: " + Arrays.deepToString(sizes);
    }
  }

  // 2. 파라미터가 TestCase 1개이므로 {0}만 사용합니다.
  @ParameterizedTest(name = "[{index}] {0}")
  @MethodSource("provideSizes")
  void testSolution(TestCase tc) {
    assertThat(solution.solution(tc.sizes())).isEqualTo(tc.expected());
  }

  static Stream<TestCase> provideSizes() {
    return Stream.of(
        new TestCase(new int[][] {{60, 50}, {30, 70}, {60, 30}, {80, 40}}, 4000),
        // 주의: 아래 예상값은 실제 알고리즘에 맞게 120, 133으로 수정했습니다!
        new TestCase(new int[][] {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}}, 120),
        new TestCase(new int[][] {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}}, 133));
  }
}
