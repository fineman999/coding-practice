package problems.pg43165;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 43165 - 타겟 넘버")
class SolutionTest {

    private final Solution solution = new Solution();

    record TestCase(int[] numbers, int target, int expected) {
        @Override
        public String toString() {
            return "expected: " + expected +
                    ", numbers: " + Arrays.toString(numbers) +
                    ", target: " + target;
        }
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @DisplayName("예제/추가 케이스")
    @MethodSource("provideTestCases")
    void test(TestCase tc) {
        int result = solution.solution(tc.numbers(), tc.target());
        assertThat(result).isEqualTo(tc.expected());
    }

    static Stream<TestCase> provideTestCases() {
        return Stream.of(
                // 문제 예제
                new TestCase(new int[]{1, 1, 1, 1, 1}, 3, 5),
                new TestCase(new int[]{4, 1, 2, 1}, 4, 2)
        );
    }
}