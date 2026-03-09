package problems.pg42862;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42862 - 체육복")
class SolutionTest {

  private final Solution solution = new Solution();

  record TestCase(int n, int[] lost, int[] reserve, int expected) {
    @Override
    public String toString() {
      return "기대 정답: "
          + expected
          + ", n: "
          + n
          + ", lost: "
          + Arrays.toString(lost)
          + ", reserve: "
          + Arrays.toString(reserve);
    }
  }

  @ParameterizedTest(name = "[{index}] {0}")
  @DisplayName("기본 케이스")
  @MethodSource("provideInputs")
  void testBasic(TestCase tc) {
    // when
    int result = solution.solution(tc.n(), tc.lost(), tc.reserve());

    // then
    assertThat(result).isEqualTo(tc.expected);
  }

  static Stream<TestCase> provideInputs() {
      return Stream.of(
              new TestCase(5, new int[]{2,4}, new int[]{1,3,5}, 5),
              new TestCase(5, new int[]{2,4}, new int[]{3}, 4),
              new TestCase(3, new int[]{3}, new int[]{1}, 2)
      );
  }
  }

