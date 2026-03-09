package problems.pg42840;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
public class SolutionBenchmark {

  private Solution solution;
  private int[] answers;

  @Setup(Level.Trial)
  public void setup() {
    solution = new Solution();

    answers = new int[10_000];

    for (int i = 0; i < 10_000; i++) {
      answers[i] = (i + 1) % 5;
    }
  }

  @Benchmark
  public int[] testSolution() {
    return solution.solution(answers);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(SolutionBenchmark.class.getSimpleName()).build();

    new Runner(opt).run();
  }
}
