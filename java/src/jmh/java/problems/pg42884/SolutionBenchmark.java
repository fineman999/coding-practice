package problems.pg42884;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 밀리초(MS)보다는 마이크로초(US)가 변별력이 높습니다
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int[][] routes;
    private Solution solution;

    @Setup
    public void setup() {
        solution = new Solution();
        routes = new int[10000][2];
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int start = random.nextInt(60000) - 30000;
            int end = start + random.nextInt(1000);
            routes[i][0] = start;
            routes[i][1] = end;
        }
    }

    @Benchmark
    public int testSolution() {
        return solution.solution(routes);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
