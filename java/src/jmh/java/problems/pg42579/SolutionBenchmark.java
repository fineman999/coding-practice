package problems.pg42579;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private String[] largeGenres;
    private int[] largePlays;

    @Setup
    public void setup() {
        // 10,000개의 데이터 생성 (장르 100종류)
        largeGenres = IntStream.range(0, 10000)
                .mapToObj(i -> "genre" + (i % 100))
                .toArray(String[]::new);
        largePlays = IntStream.range(0, 10000)
                .map(i -> (int) (Math.random() * 10000))
                .toArray();
    }

    @Benchmark
    public int[] testSolution() {
        Solution solution = new Solution();
        return solution.solution(largeGenres, largePlays);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
