package problems.pg42626;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private int[] scoville;
    private int K;

    @Setup(Level.Invocation)
    public void setUp() {
        solution = new Solution();
        K = 1_000_000_000; // 최악의 경우를 상정하여 높은 목표치

        // 10만개의 데이터 생성
        scoville = new int[100_000];
        Random random = new Random();
        for (int i = 0; i < scoville.length; i++) {
            scoville[i] = random.nextInt(1_000_000);
        }
    }

    @Benchmark
    public int testSolution() {
        return solution.solution02(scoville, K);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getName())
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}