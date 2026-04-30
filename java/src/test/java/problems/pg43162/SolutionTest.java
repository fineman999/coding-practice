package problems.pg43162;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 43162 - 네트워크")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        // given
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

        // when
        int result = solution.solution(n, computers);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("엣지 케이스 - 모두 연결되지 않음")
    void testEdge() {
        // given
        int n = 3;
        int[][] computers = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        // when
        int result = solution.solution(n, computers);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("큰 입력 - 모든 컴퓨터가 일렬로 연결")
    void testLargeInput() {
        // given
        int n = 200;
        int[][] computers = new int[n][n];
        for (int i = 0; i < n; i++) {
            computers[i][i] = 1;
            if (i < n - 1) {
                computers[i][i + 1] = 1;
                computers[i + 1][i] = 1;
            }
        }

        // when
        int result = solution.solution(n, computers);

        // then
        assertThat(result).isEqualTo(1);
    }
}
