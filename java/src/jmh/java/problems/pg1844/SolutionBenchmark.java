package problems.pg1844;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 밀리초보다 정밀한 마이크로초 권장
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private int[][] worstCaseMap;

    @Setup
    public void setup() {
        solution = new Solution();
        // BFS가 가장 오래 걸리는 100x100 S자 경로 생성
        worstCaseMap = new int[100][100];
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 100; j++) worstCaseMap[i][j] = 1;
            } else {
                int openCol = ((i / 2) % 2 == 0) ? 99 : 0;
                worstCaseMap[i][openCol] = 1;
            }
        }
        worstCaseMap[99][99] = 1;
    }

    @Benchmark
    public int testSolution() {
        // 매번 맵이 오염될 수 있다면 setup(Level.Invocation)에서 복사해야 함
        return solution.solution(worstCaseMap);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
