package problems.pg42627;

import java.util.*;

/**
 * [PG] 42627 - 디스크 컨트롤러 https://programmers.co.kr/learn/courses/30/lessons/42627 난이도: lv3 태그: heap
 *
 * <p>시간복잡도: O(N Log N) 공간복잡도: O(N)
 */
public class Solution {
  public int solution(int[][] jobs) {
    int answer = 0;
    Job[] allJobs = new Job[jobs.length];
    for (int i = 0; i < jobs.length; i++) {
      allJobs[i] = new Job(i, jobs[i][0], jobs[i][1]);
    }

    Arrays.sort(allJobs, Comparator.comparingInt(Job::startTime));
    int jIdx = 0;
    int count = 0;
    int totalAmount = 0;
    int time = 0;

    Queue<Job> jobQueue =
        new PriorityQueue<>(
            Comparator.comparing(Job::runTime)
                .thenComparingInt(Job::startTime)
                .thenComparingInt(Job::index));

    while (count < allJobs.length) {
      while (jIdx < jobs.length && allJobs[jIdx].startTime <= time) {
        jobQueue.offer(allJobs[jIdx]);
        jIdx++;
      }
      if (!jobQueue.isEmpty()) {
        Job job = jobQueue.poll();
        time += job.runTime;
        totalAmount += time - job.startTime;
        count++;
      } else {
        time = allJobs[jIdx].startTime;
      }
    }

    return totalAmount / count;
  }

  private record Job(int index, int startTime, int runTime) {}
}
