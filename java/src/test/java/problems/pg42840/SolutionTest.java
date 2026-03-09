package problems.pg42840;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42840 - 모의고사")
class SolutionTest {

    private final Solution solution = new Solution();

    record TestCase(int[] answers, int[] expected){
        @Override
        public String toString() {
            return "기대 정답:" + Arrays.toString(expected) + ", 배열:" + Arrays.toString(answers);
        }
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @DisplayName("기본 케이스")
    @MethodSource("provideAnswers")
    void testBasic(TestCase tc) {
        assertThat(solution.solution(tc.answers())).isEqualTo(tc.expected());
    }

    static Stream<TestCase> provideAnswers() {
        return Stream.of(
                new TestCase(new int[]{1,2,3,4,5},new int[]{1}),
                new TestCase(new int[]{1,3,2,4,2},new int[]{1,2,3})
                );
    }
}
