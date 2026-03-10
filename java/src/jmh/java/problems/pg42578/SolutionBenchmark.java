package problems.pg42578;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 속도가 빠를 수 있으니 마이크로초 단위로 설정
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private String[][] worstCaseClothes;

    // 벤치마크가 실행되기 전에 딱 한 번만 데이터를 세팅합니다. (데이터 세팅 시간은 측정에서 제외됨)
    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();
        worstCaseClothes = new String[1000][2];

        // 최악의 케이스: 1000개의 옷이 전부 다른 종류일 때 (Map에 계속 추가됨)
        for (int i = 0; i < 1000; i++) {
            worstCaseClothes[i][0] = "cloth" + i;
            worstCaseClothes[i][1] = "type" + i;
        }
    }

    @Benchmark
    public int testSolution() {
        // 실제 실행 시간과 메모리 할당량을 측정할 로직
        return solution.solution(worstCaseClothes);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 이 줄 덕분에 이제 '현재 문제' 딱 1개만 실행됩니다!
                .include(SolutionBenchmark.class.getName())
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}