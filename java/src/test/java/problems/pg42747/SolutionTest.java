package problems.pg42747;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42747 - H-index")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        assertThat(solution.solution(new int[]{3, 0, 6, 1, 5})).isEqualTo(3);
        assertThat(solution.solution(new int[]{10, 8, 5, 4, 3})).isEqualTo(4);
        assertThat(solution.solution(new int[]{25, 8, 5, 3, 3})).isEqualTo(3);
    }

    @Test
    @DisplayName("엣지 케이스")
    void testEdge() {
        // 모든 인용 횟수가 0인 경우
        assertThat(solution.solution(new int[]{0, 0, 0})).isEqualTo(0);
        // 논문이 1편인 경우
        assertThat(solution.solution(new int[]{10})).isEqualTo(1);
        assertThat(solution.solution(new int[]{0})).isEqualTo(0);
        // 모든 논문의 인용 횟수가 논문 수보다 많은 경우
        assertThat(solution.solution(new int[]{10, 10, 10})).isEqualTo(3);
    }

    @Test
    @DisplayName("큰 입력")
    void testLargeInput() {
        // 최대 1,000편, 모든 논문이 최대 인용(10,000)인 경우
        int[] largeInput = new int[1000];
        for (int i = 0; i < 1000; i++) largeInput[i] = 10000;

        assertThat(solution.solution(largeInput)).isEqualTo(1000);
    }
}
