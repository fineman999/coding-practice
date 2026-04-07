package problems.pg42627;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 연산이 빠르므로 마이크로초 단위 권장
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int[][] bigJobs;

    @Setup
    public void setup() {
        Random random = new Random();
        bigJobs = new int[500][2];
        for (int i = 0; i < 500; i++) {
            bigJobs[i][0] = random.nextInt(1001);     // 요청 시각 0~1000
            bigJobs[i][1] = random.nextInt(1000) + 1; // 소요 시간 1~1000
        }
    }

    @Benchmark
    public int testSolution() {
        Solution solution = new Solution();
        return solution.solution(bigJobs);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
