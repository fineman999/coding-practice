package problems.pg42862;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42862 - 체육복")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        // given

        // when
        int result = solution.solution();

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("엣지 케이스")
    void testEdge() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("큰 입력")
    void testLargeInput() {
        // given

        // when

        // then
    }
}
