package problems.pg42885;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 밀리초보다 마이크로초가 더 정밀함
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private int[] people;
    private int limit = 240;
    private Solution solution;

    @Setup(Level.Trial)
    public void setup() {
        solution = new Solution();
        people = new int[50000];
        Random random = new Random();
        for (int i = 0; i < 50000; i++) {
            people[i] = random.nextInt(201) + 40;
        }
    }

    @Benchmark
    public int testSolution() {
        // 주의: solution 내부에서 Arrays.sort()를 수행하므로
        // 매번 원본을 복사해서 전달해야 정확한 측정이 가능합니다.
        int[] copy = people.clone();
        return solution.solution(copy, limit);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
