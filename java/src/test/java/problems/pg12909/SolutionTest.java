package problems.pg12909;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 12909 - 올바른 괄호")
class SolutionTest {

  private final Solution solution = new Solution();

  @ParameterizedTest(name = "입력: {0}, 기대값: {1}")
  @CsvSource({"()(), true", "(())(), true", ")()(, false", "(()(, false", "), false", "(, false"})
  @DisplayName("기본 케이스")
  void testBasic(String s, boolean expected) {
    boolean result = solution.solution(s);

    // then
    assertThat(result).isEqualTo(expected);
  }

  @Test
  @DisplayName("큰 입력")
  void testLargeInput() {
    String s = "(".repeat(50000) + ")".repeat(50000);

    final boolean result = solution.solution(s);

    assertThat(result).isTrue();
  }

  @ParameterizedTest(name = "입력: {0}, 기대값: {1}")
  @CsvSource({"()(), true", "(())(), true", ")()(, false", "(()(, false", "), false", "(, false"})
  @DisplayName("기본 케이스")
  void testBasic02(String s, boolean expected) {
    boolean result = solution.solution02(s);

    // then
    assertThat(result).isEqualTo(expected);
  }

}
