package problems.pg42626;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42626 - 더 맵게")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        int[] scoville = new int[]{1, 2, 3, 9, 10, 12};
        int K = 7;
        assertThat(solution.solution(scoville, K)).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 음식을 섞어도 K를 넘길 수 없는 경우 -1 반환")
    void testCannotReachK() {
        int[] scoville = new int[]{1, 2, 3};
        int K = 100;
        assertThat(solution.solution(scoville, K)).isEqualTo(-1);
    }

    @Test
    @DisplayName("처음부터 모든 음식의 스코빌 지수가 K 이상인 경우 0 반환")
    void testAlreadyOverK() {
        int[] scoville = new int[]{10, 20, 30};
        int K = 5;
        assertThat(solution.solution(scoville, K)).isEqualTo(0);
    }

    @Test
    @DisplayName("원소가 2개뿐인 경우에도 정상 동작")
    void testTwoElements() {
        int[] scoville = new int[]{1, 2};
        int K = 5;
        assertThat(solution.solution(scoville, K)).isEqualTo(1);
    }

    @Test
    @DisplayName("큰 입력 (퍼포먼스 체크용)")
    void testLargeInput() {
        // given
        int size = 1_000_000;
        int[] scoville = new int[size];
        Arrays.fill(scoville, 1);
        int K = 100;

        // when
        int result = solution.solution(scoville, K);

        // then
        // 1을 섞어서 1+(1*2)=3 -> 3+(3*2)=9 -> 이런 식으로 계속 증가
        assertThat(result).isGreaterThan(0);
    }
}
