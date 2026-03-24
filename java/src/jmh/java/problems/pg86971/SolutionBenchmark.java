package problems.pg86971;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // n=100은 매우 빠르므로 마이크로초 단위 권장
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int n;
    private int[][] wires;
    private Solution solution;

    @Setup
    public void setup() {
        solution = new Solution();
        n = 100;
        wires = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            wires[i] = new int[]{i + 1, i + 2};
        }
    }

    @Benchmark
    public int testSolution() {
        return solution.solution(n, wires);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
