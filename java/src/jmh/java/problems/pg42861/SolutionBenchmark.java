package problems.pg42861;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 연산이 빠르므로 마이크로초 단위 권장
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int n;
    private int[][] costs;
    private Solution solution;

    @Setup(Level.Trial)
    public void setup() {
        solution = new Solution();
        n = 100;
        List<int[]> list = new ArrayList<>();
        // 최악의 케이스: 모든 섬이 서로 연결된 완전 그래프 생성
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                list.add(new int[]{i, j, (i + j) % 100 + 1});
            }
        }
        costs = list.toArray(new int[0][]);
    }

    @Benchmark
    public int testSolution() {
        return solution.solution(n, costs);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
