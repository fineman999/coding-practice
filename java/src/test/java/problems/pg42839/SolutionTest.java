package problems.pg42839;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42839 - 소수 찾기")
class SolutionTest {

    private final Solution solution = new Solution();

    record TestCase(String numbers, int expected) {
        @Override public String toString() {return "[Input=" + numbers + ", Expected=" + expected + "]";}
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("provideTestCases")
    @DisplayName("다양한 케이스 테스트")
    void testSolution(TestCase tc) {
        assertThat(solution.solution(tc.numbers())).isEqualTo(tc.expected());
    }

    static Stream<TestCase> provideTestCases() {
        return Stream.of(
                new TestCase("17",        3),   // 예제: 7, 17, 71
                new TestCase("011",       2),   // 예제: 11, 101
                new TestCase("1",         0),   // 소수 없음
                new TestCase("",          0),   // 빈 입력
                new TestCase("123",       5)  // 2, 3, 13, 23, 31
        );
    }
}