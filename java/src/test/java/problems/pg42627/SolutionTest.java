package problems.pg42627;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42627 - 디스크 컨트롤러")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스: 예제 입력")
    void testBasic() {
        int[][] jobs = {{0, 3}, {1, 9}, {3, 5}};
        int result = solution.solution(jobs);
        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("엣지 케이스: 모든 작업이 동시에 요청됨")
    void testSameRequestTime() {
        int[][] jobs = {{0, 10}, {0, 3}, {0, 5}};
        int result = solution.solution(jobs);
        // (3 + 8 + 18) / 3 = 9.66... -> 9
        assertThat(result).isEqualTo(9);
    }

    @Test
    @DisplayName("엣지 케이스: 작업 사이에 긴 공백이 있는 경우")
    void testWithGap() {
        int[][] jobs = {{0, 3}, {10, 5}, {11, 2}};
        int result = solution.solution(jobs);
        // 0~3(3), 10~12(1), 12~17(7) -> (3+1+7)/3 = 3.66 -> 3
        // (우선순위: 소요시간 짧은 11시작 작업이 10시작 작업보다 먼저 실행됨)
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("큰 입력: 최대 500개 작업 처리 확인")
    void testLargeInput() {
        int[][] jobs = new int[500][2];
        for (int i = 0; i < 500; i++) {
            jobs[i][0] = i;    // 매 ms마다 요청
            jobs[i][1] = 1000; // 소요시간 최대
        }
        int result = solution.solution(jobs);
        assertThat(result).isGreaterThan(0);
    }
}
