package problems.pg42895;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42895 - N으로 표현")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스 1: N=5, number=12 -> 4")
    void testBasic1() {
        assertThat(solution.solution(5, 12)).isEqualTo(4);
    }

    @Test
    @DisplayName("기본 케이스 2: N=2, number=11 -> 3")
    void testBasic2() {
        assertThat(solution.solution(2, 11)).isEqualTo(3);
    }

    @Test
    @DisplayName("엣지 케이스: N과 number가 같은 경우 -> 1")
    void testEdgeSame() {
        assertThat(solution.solution(5, 5)).isEqualTo(1);
    }

    @Test
    @DisplayName("엣지 케이스: 8번 내에 찾을 수 없는 경우 -> -1")
    void testEdgeNotFound() {
        assertThat(solution.solution(5, 31168)).isEqualTo(-1);
    }

    @Test
    @DisplayName("큰 입력: 연산량이 많은 케이스")
    void testLargeInput() {
        // N=1로 큰 수를 만드는 경우 연산량이 많아짐
        int result = solution.solution(1, 32000);
        assertThat(result).isBetween(-1, 8);
    }
}
