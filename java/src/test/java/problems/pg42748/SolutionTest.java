package problems.pg42748;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PG] 42748 - k번째수")
class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("기본 케이스")
    void testBasic() {
        // given
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = 	{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] expected = {5, 6, 3};

        // when
        int[] result = solution.solution(array, commands);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("큰 입력")
    void testLargeInput() {
        // given: 100부터 1까지 역순 배열, [1,100,50] 명령 50개
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) array[i] = 100 - i;

        int[][] commands = new int[50][3];
        for (int i = 0; i < 50; i++) commands[i] = new int[]{1, 100, 50};

        int[] expected = new int[50];
        Arrays.fill(expected, 50);

        // when
        int[] result = solution.solution(array, commands);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
