package problems.pg42577;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42577 - 전화번호 목록")
class SolutionTest {

  private final Solution solution = new Solution();

  record TestCase(String[] phoneBook, boolean expected) {
    @Override
    public String toString() {
      return "기대 정답: " + expected + ", phone_book: " + Arrays.toString(phoneBook);
    }
  }

  @ParameterizedTest(name = "[{index}] {0}")
  @DisplayName("기본 케이스")
  @MethodSource("providePhoneBook")
  void testBasic(TestCase tc) {
    // when
    boolean result = solution.solution(tc.phoneBook);

    // then
    assertThat(result).isEqualTo(tc.expected);
  }

  static Stream<TestCase> providePhoneBook() {
    return Stream.of(
        new TestCase(new String[] {"119", "97674223", "1195524421"}, false),
        new TestCase(new String[] {"123", "456", "789"}, true),
        new TestCase(new String[] {"12", "123", "1235", "567", "88"}, false));
  }
}
