package problems.pg42577;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime) // 평균 실행 시간 측정
@OutputTimeUnit(TimeUnit.MICROSECONDS) // 마이크로초 단위 출력
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SolutionBenchmark {

    private Solution solution;
    private String[] bestCasePhoneBook;
    private String[] worstCasePhoneBook;

    @Setup(Level.Trial)
    public void setUp() {
        solution = new Solution();

        // 베스트 케이스: 초반에 접두어 발견 ("12"와 "123")
        bestCasePhoneBook = new String[]{"12", "123", "1235", "567", "88"};

        // 워스트 케이스: 일치하는 접두어가 없는 대량의 배열 (1000개)
        worstCasePhoneBook = new String[1000];
        for (int i = 0; i < 1000; i++) {
            worstCasePhoneBook[i] = String.valueOf(1000000 + i); // "1000000" ~ "1000999"
        }
    }

    @Benchmark
    public boolean testBestCase() {
        return solution.solution(bestCasePhoneBook);
    }

    @Benchmark
    public boolean testWorstCase() {
        return solution.solution(worstCasePhoneBook);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .addProfiler(GCProfiler.class) // 2. GC 프로파일러 추가!
                .build();

        new Runner(opt).run();
    }
}