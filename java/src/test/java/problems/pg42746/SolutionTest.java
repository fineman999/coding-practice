package problems.pg42746;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42746 - 가장 큰 수")
class SolutionTest {

    private final Solution solution = new Solution();

    record TestCase(int[] numbers, String expected) {
        @Override
        public String toString() {
            return "expected: " + expected;
        }
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @DisplayName("기본 케이스")
    @MethodSource("provideTestCases")
    void testSolution(TestCase tc) {
        assertThat(solution.solution(tc.numbers())).isEqualTo(tc.expected());
    }

    static Stream<TestCase> provideTestCases() {
        return Stream.of(
                new TestCase(new int[]{6, 10, 2},       "6210"),
                new TestCase(new int[]{3, 30, 34, 5, 9}, "9534330"),
                new TestCase(new int[]{0, 0, 0},          "0"),    // 엣지: 모두 0
                new TestCase(new int[]{10},               "10"),   // 단일 원소
                new TestCase(new int[]{0, 1},             "10")    // 0 포함
        );
    }
}