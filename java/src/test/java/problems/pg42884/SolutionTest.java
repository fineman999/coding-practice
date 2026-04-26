package problems.pg42884;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42884 - 단속카메라")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스: 프로그래머스 예시")
    void testBasic() {
        // given
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};

        // when
        int result = solution.solution(routes);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("엣지 케이스: 경로가 겹치거나 맞닿는 경우")
    void testEdge() {
        // 모든 차량이 한 지점(0)에서 만날 때
        assertThat(solution.solution(new int[][]{{-10, 0}, {0, 10}})).isEqualTo(1);

        // 차량이 1대일 때
        assertThat(solution.solution(new int[][]{{-30000, 30000}})).isEqualTo(1);

        // 경로가 모두 떨어져 있을 때
        assertThat(solution.solution(new int[][]{{1, 2}, {3, 4}, {5, 6}})).isEqualTo(3);
    }

    @Test
    @DisplayName("큰 입력: 최대 10,000대 차량")
    void testLargeInput() {
        // given: 10,000대의 차량이 서로 겹치지 않게 진입/진출
        int[][] routes = new int[10000][2];
        for (int i = 0; i < 10000; i++) {
            routes[i][0] = i * 2;
            routes[i][1] = i * 2 + 1;
        }

        // when
        int result = solution.solution(routes);

        // then: 모든 차량이 떨어져 있으므로 카메라 10,000대 필요
        assertThat(result).isEqualTo(10000);
    }
}
