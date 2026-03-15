package problems.pg42860;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42860 - 조이스틱")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test @DisplayName("기본 케이스")
    void testBasic() {
        assertThat(solution.solution("JEROEN")).isEqualTo(56);
        assertThat(solution.solution("JAN")).isEqualTo(23);
    }
}