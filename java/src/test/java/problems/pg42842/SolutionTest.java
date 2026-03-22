package problems.pg42842;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42842 - 카펫")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("입출력 예시 케이스")
    void testBasic() {
        // 입출력 예 1: brown 10, yellow 2 -> [4, 3]
        assertThat(solution.solution(10, 2)).containsExactly(4, 3);

        // 입출력 예 2: brown 8, yellow 1 -> [3, 3]
        assertThat(solution.solution(8, 1)).containsExactly(3, 3);

        // 입출력 예 3: brown 24, yellow 24 -> [8, 6]
        assertThat(solution.solution(24, 24)).containsExactly(8, 6);
    }

    @Test
    @DisplayName("최소 크기 케이스 (brown 8, yellow 1)")
    void testEdge() {
        // 가장 작은 카펫 사이즈
        int[] result = solution.solution(8, 1);
        assertThat(result).containsExactly(3, 3);
    }

    @Test
    @DisplayName("가로가 훨씬 긴 경우")
    void testLongHorizontal() {
        // yellow가 일렬로 배치된 경우 (예: yellow 3개, brown 12개 -> [5, 3])
        assertThat(solution.solution(12, 3)).containsExactly(5, 3);
    }

    @Test
    @DisplayName("최대 입력값 근사 케이스")
    void testLargeInput() {
        // brown 5000, yellow 2,000,000 범위 내 임의의 큰 값 (예: 가로 2002, 세로 1002)
        // yellow = 2000 * 1000 = 2,000,000
        // brown = (2002 * 2) + (1000 * 2) = 4004 + 2000 = 6004 (제한사항 5000 살짝 초과나 논리 확인용)
        // 제한사항 내 적절한 큰 값 예: brown 4004, yellow 1,000,000 -> [1002, 1002]
        assertThat(solution.solution(4004, 1000000)).containsExactly(1002, 1002);
    }
}
