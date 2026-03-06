package problems.pg42748;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread) // 객체의 상태(배열 등)를 벤치마크 내에서 공유
@BenchmarkMode(Mode.AverageTime) // Go 벤치마크처럼 '1회당 평균 실행 시간' 측정
@OutputTimeUnit(TimeUnit.NANOSECONDS) // 결과를 나노초(ns/op) 단위로 출력
@Warmup(iterations = 2, time = 1) // JVM 예열 2번 (실제 측정엔 안 들어감)
@Measurement(iterations = 3, time = 1) // 실제 측정 3번 (알고리즘 풀이용이라 짧게 설정)
@Fork(1) // 완전히 새로운 JVM 프로세스를 1개 띄워서 깔끔하게 측정
public class SolutionBenchmark {

    private Solution solution;
    private int[] array;
    private int[][] commands;

    // Go의 b.ResetTimer() 및 데이터 세팅과 완벽히 동일한 역할
    @Setup(Level.Trial)
    public void setup() {
        solution = new Solution();

        // 100부터 1까지 역순 배열 생성 (최악의 케이스)
        array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = 100 - i;
        }

        // 전체를 자르고 50번째를 찾는 명령 50개 생성
        commands = new int[50][3];
        for (int i = 0; i < 50; i++) {
            commands[i] = new int[]{1, 100, 50};
        }
    }

    // 실제 성능을 측정할 메서드 (Go의 for 루프 안쪽 역할)
    @Benchmark
    public int[] testSolution() {
        // 주의: JVM의 똑똑한 최적화(결과를 안 쓰면 아예 실행을 안 해버림)를 막기 위해
        // 반드시 실행 결과를 return 해주어야 합니다!
        return solution.solution(array, commands);
    }

    // IntelliJ 등 IDE에서 바로 우클릭 -> Run 하기 위한 메인 메서드
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}