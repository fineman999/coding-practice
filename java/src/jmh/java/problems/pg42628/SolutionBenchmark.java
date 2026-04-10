package problems.pg42628;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private String[] largeOps;

    @Setup
    public void setup() {
        // 1,000,000개의 연산 준비 (제한사항 최대치)
        largeOps = new String[1000000];
        for (int i = 0; i < 1000000; i++) {
            if (i < 500000) {
                largeOps[i] = "I " + i;
            } else {
                largeOps[i] = (i % 2 == 0) ? "D 1" : "D -1";
            }
        }
    }

    @Benchmark
    public int[] testSolution() {
        Solution solution = new Solution();
        return solution.solution(largeOps);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
