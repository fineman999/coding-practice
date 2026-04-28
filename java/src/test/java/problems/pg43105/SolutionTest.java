package problems.pg43105;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 43105 - 정수 삼각형")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스 - 프로그래머스 예제")
    void testBasic() {
        // given
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };

        // when
        int result = solution.solution(triangle);

        // then
        assertThat(result).isEqualTo(30);
    }

    @Test
    @DisplayName("엣지 케이스 - 높이가 1인 경우")
    void testEdge() {
        // given
        int[][] triangle = {{100}};

        // when
        int result = solution.solution(triangle);

        // then
        assertThat(result).isEqualTo(100);
    }

    @Test
    @DisplayName("큰 입력 - 높이 500")
    void testLargeInput() {
        // given
        int height = 500;
        int[][] triangle = new int[height][];
        for (int i = 0; i < height; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = 1; // 모든 값을 1로 채움
            }
        }

        // when
        int result = solution.solution(triangle);

        // then
        assertThat(result).isEqualTo(500);
    }
}
