package problems.pg42587;

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

    private Solution solution;
    private int[] priorities;
    private int location;

    @Setup
    public void setUp() {
        solution = new Solution();

        // 벤치마크용 입력값 셋팅
        // 예: [2, 1, 3, 2], location=2 (문제 기본 예시)
        // priorities = new int[]{2, 1, 3, 2};
        // location = 2;

        // 더 큰 입력 (ex: priorities 배열이 100, 랜덤, 최대값 등)
        priorities = new int[100];
        for (int i = 0; i < priorities.length; i++) {
            priorities[i] = (i % 9) + 1;
        }
        location = 99;
    }

    @Benchmark
    public int testSolution() {
        return solution.solution(priorities, location);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
