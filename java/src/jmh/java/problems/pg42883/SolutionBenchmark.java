package problems.pg42883;

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
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
public class SolutionBenchmark {

    private String largeNumber;
    private int k;

    @Setup
    public void setup() {
        // 백만 자리 데이터 준비 (Setup 시간은 측정에서 제외됨)
        this.largeNumber = "1".repeat(500000) + "2".repeat(500000);
        this.k = 500000;
    }

    @Benchmark
    public String testSolution() {
        Solution solution = new Solution();
        return solution.solution(largeNumber, k);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
