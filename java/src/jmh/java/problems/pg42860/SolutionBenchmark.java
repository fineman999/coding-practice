package problems.pg42860;

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

    private final Solution solution = new Solution();
    private final String maxCase = "Z".repeat(20);

    @Benchmark
    public int testSolution() {
        return solution.solution(maxCase);
    }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getName())
                .build();
        new Runner(opt).run();
    }
}