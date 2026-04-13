package problems.pg42861;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42861 - 섬 연결하기")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스: 프로그래머스 예시")
    void testBasic() {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};

        int result = solution.solution(n, costs);

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("엣지 케이스: 최소 입력(섬 1개) 및 2개")
    void testEdge() {
        // 섬이 1개일 때
        assertThat(solution.solution(1, new int[][]{})).isEqualTo(0);

        // 섬이 2개고 다리가 1개일 때
        assertThat(solution.solution(2, new int[][]{{0, 1, 5}})).isEqualTo(5);
    }

    @Test
    @DisplayName("특이 케이스: 모든 비용이 동일하거나 일직선 구조")
    void testSpecial() {
        // 모든 비용이 동일할 때 (n=3, 비용 10 -> 20)
        int[][] sameCosts = {{0, 1, 10}, {1, 2, 10}, {0, 2, 10}};
        assertThat(solution.solution(3, sameCosts)).isEqualTo(20);

        // 가장 비싼 다리를 피해야 하는 경우
        int[][] avoidExpensive = {{0, 1, 1}, {1, 2, 2}, {2, 3, 3}, {0, 3, 100}};
        assertThat(solution.solution(4, avoidExpensive)).isEqualTo(6);
    }
}
