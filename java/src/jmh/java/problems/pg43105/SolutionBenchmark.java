package problems.pg43105;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 연산 속도가 빠르므로 MICROSECONDS 권장
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int[][] triangle;
    private Solution solution;

    @Setup(Level.Trial)
    public void setup() {
        solution = new Solution();
        int height = 500;
        triangle = new int[height][];
        for (int i = 0; i < height; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = (int) (Math.random() * 10000);
            }
        }
    }

    @Benchmark
    public int testSolution() {
        return solution.solution(triangle);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
