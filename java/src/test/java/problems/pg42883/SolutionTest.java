package problems.pg42883;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42883 - 큰 수 만들기")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        assertThat(solution.solution("1924", 2)).isEqualTo("94");
        assertThat(solution.solution("1231234", 3)).isEqualTo("3234");
        assertThat(solution.solution("4177252841", 4)).isEqualTo("775841");
    }

    @Test
    @DisplayName("엣지 케이스: 내림차순 및 동일 숫자")
    void testEdge() {
        // 내림차순일 때 뒤가 잘리는지 확인
        assertThat(solution.solution("9876", 2)).isEqualTo("98");
        // 동일한 숫자가 반복될 때
        assertThat(solution.solution("1111", 2)).isEqualTo("11");
        // 결과가 한 자리인 경우
        assertThat(solution.solution("10", 1)).isEqualTo("1");
    }

    @Test
    @DisplayName("큰 입력: 1,000,000자리 처리")
    void testLargeInput() {
        // 1이 50만개, 2가 50만개인 숫자 생성 (StringBuilder 사용)
        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(500000));
        sb.append("2".repeat(500000));

        String result = solution.solution(sb.toString(), 500000);

        assertThat(result).isEqualTo("2".repeat(500000));
    }
}
