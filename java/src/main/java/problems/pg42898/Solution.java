package problems.pg42898;

/**
 * [PG] 42898 - 등굣길
 * https://programmers.co.kr/learn/courses/30/lessons/42898
 * 난이도: lv3
 * 태그: DP
 *
 * 시간복잡도: O(NM)
 * 공간복잡도: O(NM)
 */
public class Solution {
    private static final int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {

        int[][] graph = new int[n+1][m+1];
        for (final int[] puddle : puddles) {
            graph[puddle[1]][puddle[0]] = -1;
        }
        graph[1][1] = 1;
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (graph[y][x] == 0) {
                    int up = 0;
                    int left = 0;
                    if (graph[y-1][x] != -1) {
                        up = graph[y-1][x];
                    }
                    if (graph[y][x-1] != -1) {
                        left = graph[y][x-1];
                    }
                    graph[y][x] = (up + left) % MOD;
                }
            }
        }

        return graph[n][m] % MOD;
    }
}
