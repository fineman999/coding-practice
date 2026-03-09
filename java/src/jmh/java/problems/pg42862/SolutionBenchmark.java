package problems.pg42862;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private int n;
    private int[] lost;
    private int[] reserve;

    @Setup
    public void setup() {
        solution = new Solution();
        n = 30;
        lost = new int[15];
        reserve = new int[15];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) lost[i / 2] = i + 1;
            else             reserve[i / 2] = i + 1;
        }
    }

    @Benchmark
    public int testSolution() {
        return solution.solution(n, lost, reserve);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
