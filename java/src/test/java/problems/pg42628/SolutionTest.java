package problems.pg42628;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42628 - 이중우선순위큐")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스 1 - 입출력 예시 1")
    void testBasic1() {
        String[] ops = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        int[] result = solution.solution(ops);
        assertThat(result).containsExactly(0, 0);
    }

    @Test
    @DisplayName("기본 케이스 2 - 입출력 예시 2")
    void testBasic2() {
        String[] ops = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] result = solution.solution(ops);
        assertThat(result).containsExactly(333, -45);
    }

    @Test
    @DisplayName("엣지 케이스 - 빈 큐 삭제 및 중복 데이터")
    void testEdge() {
        // 빈 큐에서 삭제 시도
        assertThat(solution.solution(new String[]{"D 1", "D -1"})).containsExactly(0, 0);

        // 동일 값 중복 삽입 후 하나만 삭제
        String[] ops = {"I 10", "I 10", "I 10", "D 1"};
        assertThat(solution.solution(ops)).containsExactly(10, 10);
    }

    @Test
    @DisplayName("큰 입력 - 대량의 데이터 처리")
    void testLargeInput() {
        String[] ops = new String[100000];
        for (int i = 0; i < 100000; i++) {
            ops[i] = (i % 2 == 0) ? "I " + i : "D 1";
        }
        int[] result = solution.solution(ops);
        assertThat(result).hasSize(2);
    }
}
