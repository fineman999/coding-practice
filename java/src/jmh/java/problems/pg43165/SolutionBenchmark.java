package problems.pg43165;

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

    private int[] largeInput;
    private int largeTarget;

    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();

        // 큰 입력 (길이 20, 모든 값은 1)
        largeInput = new int[20];
        for (int i = 0; i < 20; i++) largeInput[i] = 1;
        largeTarget = 10;
    }

    @Benchmark
    public int testLargeInput() {
        return solution.solution(largeInput, largeTarget);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getName())
                .addProfiler(GCProfiler.class) // 2. GC 프로파일러 추가!
                .build();
        new Runner(opt).run();
    }
}