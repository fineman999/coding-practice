package problems.pg42583;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 자바는 빨라서 마이크로초 단위가 보기 편합니다
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int bridgeLength;
    private int weight;
    private int[] truckWeights;

    @Setup
    public void setup() {
        bridgeLength = 10000;
        weight = 10000;
        truckWeights = new int[10000];
        for (int i = 0; i < 10000; i++) {
            truckWeights[i] = 1;
        }
    }

    @Benchmark
    public int testWorstCase() {
        Solution solution = new Solution();
        // 실제 프로그래머스 환경과 유사하게 매번 호출
        return solution.solution(bridgeLength, weight, truckWeights);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getName())
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
