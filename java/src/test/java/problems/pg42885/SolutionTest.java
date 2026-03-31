package problems.pg42885;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42885 - 구명보트")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        assertThat(solution.solution(new int[]{70, 50, 80, 50}, 100)).isEqualTo(3);
        assertThat(solution.solution(new int[]{70, 80, 50}, 100)).isEqualTo(3);
    }

    @Test
    @DisplayName("엣지 케이스")
    void testEdge() {
        // 혼자 있는 경우
        assertThat(solution.solution(new int[]{40}, 100)).isEqualTo(1);
        // 모두가 무거워 같이 못 타는 경우
        assertThat(solution.solution(new int[]{60, 60, 60}, 100)).isEqualTo(3);
        // 무게 합이 딱 limit인 경우
        assertThat(solution.solution(new int[]{50, 50}, 100)).isEqualTo(1);
    }

    @Test
    @DisplayName("큰 입력")
    void testLargeInput() {
        int[] people = new int[50000];
        for (int i = 0; i < 50000; i++) {
            people[i] = (i % 2 == 0) ? 40 : 200; // 40, 200 조합
        }
        assertThat(solution.solution(people, 240)).isEqualTo(25000);
    }
}
