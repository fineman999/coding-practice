package problems.pg42587;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42587 - 프로세스")
class SolutionTest {

    private final Solution solution = new Solution();

    record TestCase(int[] priorities, int location, int expected) {
        @Override
        public String toString() {
            return "기대 정답: " + expected +
                    ", priorities: " + Arrays.toString(priorities) +
                    ", location: " + location;
        }
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @DisplayName("기본 및 엣지 케이스")
    @MethodSource("provideInputs")
    void testBasic(TestCase tc) {
        // when
        int result = solution.solution(tc.priorities(), tc.location());

        // then
        assertThat(result).isEqualTo(tc.expected());
    }

    static Stream<TestCase> provideInputs() {
        return Stream.of(
                // 문제 예시 1
                new TestCase(new int[]{2, 1, 3, 2}, 2, 1),
                // 문제 예시 2
                new TestCase(new int[]{1, 1, 9, 1, 1, 1}, 0, 5),
                // location이 맨 뒤
                new TestCase(new int[]{1, 2, 3, 4}, 2, 2),
                // 모두 같은 우선순위
                new TestCase(new int[]{3, 3, 3, 3}, 2, 3)
        );
    }
}
