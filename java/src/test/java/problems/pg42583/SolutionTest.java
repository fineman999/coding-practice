package problems.pg42583;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42583 - 다리를 지나는 트럭")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 예제 케이스들")
    void testBasic() {
        // 예제 1
        assertThat(solution.solution(2, 10, new int[]{7, 4, 5, 6})).isEqualTo(8);
        // 예제 2
        assertThat(solution.solution(100, 100, new int[]{10})).isEqualTo(101);
        // 예제 3
        assertThat(solution.solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10})).isEqualTo(110);
    }

    @Test
    @DisplayName("엣지 케이스: 트럭이 1대이거나 다리 길이가 1인 경우")
    void testEdge() {
        // 다리 길이 1, 무게 10, 트럭 1대 (1초 진입, 2초 탈출)
        assertThat(solution.solution(1, 10, new int[]{5})).isEqualTo(2);
        // 무게 제한에 딱 걸리는 연속된 트럭
        assertThat(solution.solution(5, 10, new int[]{10, 10})).isEqualTo(11);
    }

    @Test
    @DisplayName("큰 입력: 모든 트럭이 무게 1이며 최대 개수일 때")
    void testLargeInput() {
        int bridgeLength = 10000;
        int weight = 10000;
        int[] truckWeights = new int[10000];
        for (int i = 0; i < 10000; i++) truckWeights[i] = 1;

        int result = solution.solution(bridgeLength, weight, truckWeights);

        // 1만대가 들어가는데 1만초, 마지막 트럭이 나오는데 1만초
        assertThat(result).isEqualTo(20000);
    }
}

