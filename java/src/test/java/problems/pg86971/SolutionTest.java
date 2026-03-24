package problems.pg86971;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 86971 - 전력망을 둘로 나누기")
class SolutionTest {

    private final Solution sol = new Solution();

    @Test
    @DisplayName("기본 케이스: 프로그래머스 예제")
    void testBasic() {
        // 예제 1
        assertThat(sol.solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}))
                .isEqualTo(3);
        // 예제 2
        assertThat(sol.solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}))
                .isEqualTo(0);
        // 예제 3
        assertThat(sol.solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}))
                .isEqualTo(1);
    }

    @Test
    @DisplayName("엣지 케이스: 최소 입력 및 스타 그래프")
    void testEdge() {
        // n=2 (최소)
        assertThat(sol.solution(2, new int[][]{{1, 2}})).isEqualTo(0);

        // 모든 노드가 1번에 연결된 경우 (n=5)
        assertThat(sol.solution(5, new int[][]{{1, 2}, {1, 3}, {1, 4}, {1, 5}})).isEqualTo(3);
    }

    @Test
    @DisplayName("큰 입력: n=100 일직선 연결")
    void testLargeInput() {
        int n = 100;
        int[][] wires = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            wires[i] = new int[]{i + 1, i + 2};
        }
        // 100개 노드가 일렬이면 50:50으로 나뉘어 차이가 0이어야 함
        assertThat(sol.solution(n, wires)).isEqualTo(0);
    }
}
