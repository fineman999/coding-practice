package problems.pg42746;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
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

    private Solution solution;
    private int[] worstCase;

    // 데이터 세팅 시간은 측정에서 제외 (Go의 b.ResetTimer() 와 동일)
    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();

        // 최악의 케이스: 100,000개, 원소 0~999 순환 (문자열 비교 최대)
        worstCase = new int[100_000];
        for (int i = 0; i < 100_000; i++) {
            worstCase[i] = i % 1000;
        }
    }

    @Benchmark
    public String testSolution() {
        // ✅ 반드시 결과를 return (JMH의 Dead Code Elimination 방지)
        return solution.solution(worstCase);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getName()) // 이 벤치마크만 실행
                .addProfiler(GCProfiler.class)              // GC 메모리 측정 추가
                .build();
        new Runner(opt).run();
    }
}