package problems.pg12909;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 속도가 빨라 마이크로초 단위로 변경
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private String largeInput;

    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();
        // 10만 사이즈의 입력값 벤치마크 전 사전 세팅 (시간 측정에서 제외됨)
        largeInput = "(".repeat(50000) + ")".repeat(50000);
    }

    @Benchmark
    public boolean testSolution() {
        return solution.solution(largeInput);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}