package problems.pg87946;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 87946 - 피로도")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스: 프로그래머스 예제")
    void testBasic() {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        assertThat(solution.solution(k, dungeons)).isEqualTo(3);
    }

    @Test
    @DisplayName("엣지 케이스: 피로도가 부족해 하나도 못 도는 경우")
    void testNoPossible() {
        int k = 10;
        int[][] dungeons = {{80, 20}, {50, 40}};
        assertThat(solution.solution(k, dungeons)).isEqualTo(0);
    }

    @Test
    @DisplayName("엣지 케이스: 던전이 하나일 때")
    void testSingleDungeon() {
        assertThat(solution.solution(50, new int[][]{{50, 10}})).isEqualTo(1);
        assertThat(solution.solution(40, new int[][]{{50, 10}})).isEqualTo(0);
    }

    @Test
    @DisplayName("큰 입력: 최대 개수(8개) 탐험 가능")
    void testLargeInput() {
        int k = 5000;
        int[][] dungeons = new int[8][2];
        for (int i = 0; i < 8; i++) {
            dungeons[i] = new int[]{1, 1}; // 모든 던전 최소 1, 소모 1
        }
        assertThat(solution.solution(k, dungeons)).isEqualTo(8);
    }
}

