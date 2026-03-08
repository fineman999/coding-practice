package problems.pg86491;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(1)
public class SolutionBenchmark {

  private Solution solution;
  private int[][] sizes;

  @Setup(Level.Trial)
  public void setup() {
    solution = new Solution();

    // 제한사항 최대: 10,000개, 값 범위 1~1,000
    sizes = new int[10_000][2];
    for (int i = 0; i < 10_000; i++) {
      sizes[i] =
          new int[] {
            (i % 1_000) + 1, // 1~1,000 순환
            1_000 - (i % 1_000) // 역방향 순환
          };
    }
  }

  @Benchmark
  public int testSolution() {
    // int 반환 → JMH가 Dead Code Elimination 방지
    return solution.solution(sizes);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(SolutionBenchmark.class.getSimpleName()).build();
    new Runner(opt).run();
  }
}
