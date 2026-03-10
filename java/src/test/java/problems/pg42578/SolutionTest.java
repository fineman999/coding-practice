package problems.pg42578;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42578 - 의상")
class SolutionTest {

  private final Solution solution = new Solution();

  record TestCase(String[][] clothes, int expected) {
    @Override
    public String toString() {
      return "기대 정답: " + expected + ", clothes: " + Arrays.toString(clothes);
    }
  }

  @ParameterizedTest(name = "[{index}] {0}")
  @DisplayName("기본 케이스")
  @MethodSource("provideClothes")
  void testBasic(TestCase tc) {
    int result = solution.solution(tc.clothes);

    // then
    assertThat(result).isEqualTo(tc.expected);
  }

  static Stream<TestCase> provideClothes() {
    return Stream.of(
        new TestCase(
            new String[][] {
              {"yellow_hat", "headgear"},
              {"blue_sunglasses", "eyewear"},
              {"green_turban", "headgear"}
            },
            5),
        new TestCase(
            new String[][] {
              {"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}
            },
            3));
  }
}
