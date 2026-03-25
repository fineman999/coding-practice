package problems.pg84512;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime) // 평균 소요 시간 측정
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 단위를 마이크로초로 변경 (연산이 매우 빠름)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private String worstCaseWord;

    @Setup
    public void setup() {
        solution = new Solution();
        worstCaseWord = "UUUUU";
    }

    @Benchmark
    public int testSolution() {
        // 최악의 케이스(가장 마지막 단어)를 탐색할 때의 성능 측정
        return solution.solution(worstCaseWord);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
