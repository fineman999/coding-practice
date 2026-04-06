package problems.pg42579;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42579 - 베스트앨범")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스: 프로그래머스 예시")
    void testBasic() {
        // given
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        // when
        int[] result = solution.solution(genres, plays);

        // then
        assertThat(result).containsExactly(4, 1, 3, 0);
    }

    @Test
    @DisplayName("엣지 케이스: 장르에 곡이 하나만 있는 경우")
    void testEdge() {
        // given
        String[] genres = {"classic", "pop", "classic", "jazz"};
        int[] plays = {500, 600, 150, 3000};

        // when
        // jazz(3000) > classic(650) > pop(600) 순서
        int[] result = solution.solution(genres, plays);

        // then
        assertThat(result).containsExactly(3, 0, 2, 1);
    }

    @Test
    @DisplayName("특수 케이스: 재생 횟수가 같을 때 고유 번호 낮은 순")
    void testSamePlayCount() {
        // given
        String[] genres = {"pop", "pop", "pop"};
        int[] plays = {500, 500, 500};

        // when
        int[] result = solution.solution(genres, plays);

        // then
        assertThat(result).containsExactly(0, 1);
    }
}
