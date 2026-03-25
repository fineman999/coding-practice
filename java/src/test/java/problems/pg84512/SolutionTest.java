package problems.pg84512;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 84512 - 모음사전")
class SolutionTest {

    private final Solution solution = new Solution();

    @ParameterizedTest
    @CsvSource({
            "AAAAE, 6",
            "AAAE, 10",
            "I, 1563",
            "EIO, 1189"
    })
    @DisplayName("기본 입출력 예시 검증")
    void testBasic(String word, int expected) {
        assertThat(solution.solution(word)).isEqualTo(expected);
    }

    @Test
    @DisplayName("엣지 케이스: 사전의 처음과 끝")
    void testEdge() {
        assertThat(solution.solution("A")).isEqualTo(1);
        assertThat(solution.solution("UUUUU")).isEqualTo(3905);
    }

    @Test
    @DisplayName("입력 길이별 검증")
    void testLength() {
        // 길이가 1인 경우 중 가장 뒤
        assertThat(solution.solution("U")).isEqualTo(3125);
        // 길이가 5인 경우 중 가장 앞
        assertThat(solution.solution("AAAAA")).isEqualTo(5);
    }
}
