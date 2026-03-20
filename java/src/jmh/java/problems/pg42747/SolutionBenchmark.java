package problems.pg42747;

import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 밀리초(ms)보다 마이크로초(us)가 더 정밀합니다.
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int[] citations;

    @Setup
    public void setup() {
        // 1,000개의 논문 데이터를 랜덤하게 생성하여 벤치마크 준비
        citations = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            citations[i] = random.nextInt(10001);
        }
    }

    @Benchmark
    public int testSolution() {
        Solution solution = new Solution();
        return solution.solution(citations);
    }
}
