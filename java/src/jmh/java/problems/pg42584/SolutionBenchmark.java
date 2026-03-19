package problems.pg42584;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private int[] largePrices;

    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();
        // 제한사항: prices의 길이는 최대 100,000
        largePrices = new int[100_000];
        for (int i = 0; i < 100_000; i++) {
            largePrices[i] = i % 10000 + 1;
        }
    }

    @Benchmark
    public int[] testSolution() {
        return solution.solution(largePrices);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
