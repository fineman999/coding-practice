package problems.pg1844;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 1844 - 게임 맵 최단거리")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스 1: 도달 가능한 경우")
    void testBasicSuccess() {
        int[][] maps = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };
        assertThat(solution.solution(maps)).isEqualTo(11);
    }

    @Test
    @DisplayName("기본 케이스 2: 도달 불가능한 경우")
    void testBasicFailure() {
        int[][] maps = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,0},
                {0,0,0,0,1}
        };
        assertThat(solution.solution(maps)).isEqualTo(-1);
    }

    @Test
    @DisplayName("엣지 케이스: 최소 크기(2x2) 및 막힌 시작점")
    void testEdge() {
        // 2x2 최단거리
        assertThat(solution.solution(new int[][]{{1, 1}, {1, 1}})).isEqualTo(3);
        // 시작점 직후가 모두 벽인 경우
        assertThat(solution.solution(new int[][]{{1, 0}, {0, 1}})).isEqualTo(-1);
    }

    @Test
    @DisplayName("큰 입력: 100x100 모든 공간이 길인 경우")
    void testLargeInput() {
        int[][] largeMap = new int[100][100];
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) largeMap[i][j] = 1;
        }
        // (0,0)에서 (99,99)까지 최단거리는 n + m - 1
        assertThat(solution.solution(largeMap)).isEqualTo(199);
    }
}
