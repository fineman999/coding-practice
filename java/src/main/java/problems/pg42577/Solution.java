package problems.pg42577;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * [PG] 42577 - 전화번호 목록 https://programmers.co.kr/learn/courses/30/lessons/42577 난이도: lv1 태그: hash
 *
 * <p>시간복잡도:
 * 해시: O(n*l) 공간복잡도: O(n)
 * 정렬: O(LxNlogN) 공간복잡도: O(n)
 * ### 📊 GC 프로파일링 결과 비교 (Worst Case 기준)
 *
 * | 측정 항목 | 1. 정렬(Sort) | 2. 해시(Hash) | 비교 결과 |
 * | :--- | :--- | :--- | :--- |
 * | **실행 속도** (`Score`) | **12.082 us/op** | 71.638 us/op | 정렬이 약 **6배** 빠름 |
 * | **1회당 메모리 할당량** (`gc.alloc.rate.norm`) | **1,320 B/op** (약 1.3KB) | **328,304 B/op** (약 328KB) | **해시가 약 250배 많은 메모리 소모!** |
 * | **GC 실행 횟수** (`gc.count`) | **4 회** | **74 회** | **해시에서 가비지 컬렉터가 18배 더 많이 일함** |
 *
 * 시간복잡도는 해시가 빠르지만 실제 GC 컴파일러 결과가 정렬이 빠른 이유
 * Java에서는 String은 불변 객체이다. 따라서 subString을 호출 할때마다 새로운 배열이 메모리(Heap)에 계속 생성됨
 * 반복문이 돌때마다 객체가 발생하니, 메모리 할당량이 급증하고, 꽉찬 메모리를 비우기위해 GC가 발생
 * 그러므로 객체 생성 비용과 GC 오버헤드로 인한 속도 차이가 발생
 **/
public class Solution {

  public boolean solution(String[] phone_book) {
    return usingHash(phone_book);
  }

  // 해시 기반
  private boolean usingHash(final String[] phone_book) {
    Set<String> set = new HashSet<>(Arrays.asList(phone_book));
    for (final String phone : phone_book) {
      for (int i = 1; i < phone.length(); i++) {
        if (set.contains(phone.substring(0, i))) {
          return false;
        }
      }
    }
    return true;
  }
  // 정렬 기반
  private boolean usingSort(final String[] phone_book) {
    Arrays.sort(phone_book);

    return IntStream.range(0, phone_book.length-1).noneMatch(i -> phone_book[i + 1].startsWith(phone_book[i]));
  }
}
