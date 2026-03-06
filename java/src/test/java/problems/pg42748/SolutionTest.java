package problems.pg42748;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42748 - k번째수")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        // given

        // when
        int result = solution.solve();

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
