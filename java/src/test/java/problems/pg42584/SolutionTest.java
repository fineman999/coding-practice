package problems.pg42584;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42584 - 주식가격")
class SolutionTest {

    private final Solution solution = new Solution();

    record TestCase(int[] prices, int[] expected) {
        @Override
        public String toString() {
            return "입력: " + Arrays.toString(prices) + ", 기대값: " + Arrays.toString(expected);
        }
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("provideTestCases")
    void testSolution(TestCase tc) {
        // when
        int[] result = solution.solution(tc.prices);

        // then
        assertThat(result).containsExactly(tc.expected);
    }

    static Stream<TestCase> provideTestCases() {
        return Stream.of(
                new TestCase(new int[]{1, 2, 3, 2, 3}, new int[]{4, 3, 1, 1, 0}), // 예시 1
                new TestCase(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 2, 1, 0}), // 계속 상승
                new TestCase(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 1, 1, 0}), // 계속 하락
                new TestCase(new int[]{1, 2, 3, 1, 3}, new int[]{4, 2, 1, 1, 0})  // 아까 분석한 케이스
        );
    }
}
