package problems.pg42839;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
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
    private String easyCase;
    private String midCase;
    private String hardCase;

    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();

        easyCase = "17";          // 작은 케이스, 예제
        midCase = "11117";        // 중간 크기(약간 긴 입력)
        hardCase = "9876543";     // 최악, 강력한 테스트 (7자리 모든 순열)
    }

    @Benchmark
    public int testEasyCase() {
        return solution.solution02(easyCase);
    }

    @Benchmark
    public int testMidCase() {
        return solution.solution02(midCase);
    }

    @Benchmark
    public int testHardCase() {
        return solution.solution02(hardCase);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getName())
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}