package problems.pg42898;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42898 - 등굣길")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스 - 프로그래머스 예시")
    void testBasic() {
        // given
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};

        // when
        int result = solution.solution(m, n, puddles);

        // then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("엣지 케이스 - 장애물로 길이 막힌 경우")
    void testEdge() {
        // given
        int m = 3;
        int n = 3;
        int[][] puddles = {{1, 2}, {2, 1}};

        // when
        int result = solution.solution(m, n, puddles);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("큰 입력 - 100x100 웅덩이 없음")
    void testLargeInput() {
        // given
        int m = 100;
        int n = 100;
        int[][] puddles = {};

        // when
        int result = solution.solution(m, n, puddles);

        // then
        // 최대 경로 수의 나머지값 확인 (효율성 및 오버플로우 체크)
        assertThat(result).isBetween(0, 1000000006);
        assertThat(result).isEqualTo(690285631);
    }
}
