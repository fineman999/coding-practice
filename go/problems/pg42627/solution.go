// Package pg42627 - [PG] 42627 - 디스크 컨트롤러
// https://programmers.co.kr/learn/courses/30/lessons/42627
// 난이도: lv3
// 태그: heap
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg42627

import (
	"container/heap"
	"sort"
)

type Job struct {
	index     int
	startTime int
	runTime   int
}

type JobHeap struct {
	data []Job
	less func(a, b Job) bool
}

func (h *JobHeap) Len() int {
	return len(h.data)
}

func (h *JobHeap) Less(i, j int) bool {
	if h.data[i].runTime != h.data[j].runTime {
		return h.data[i].runTime < h.data[j].runTime
	}
	if h.data[i].startTime != h.data[j].startTime {
		return h.data[i].startTime < h.data[j].startTime
	}
	return h.data[i].index < h.data[j].index
}

func (h *JobHeap) Swap(i, j int) {
	h.data[i], h.data[j] = h.data[j], h.data[i]
}

func (h *JobHeap) Push(x any) {
	h.data = append(h.data, x.(Job))
}

func (h *JobHeap) Pop() any {
	n := len(h.data)
	x := h.data[n-1]
	h.data = h.data[:n-1]
	return x
}

func Solution(jobs [][]int) int {
	jobList := make([]Job, len(jobs))
	for i, job := range jobs {
		jobList[i] = Job{
			index:     i,
			startTime: job[0],
			runTime:   job[1],
		}
	}
	j := &JobHeap{
		data: []Job{},
	}
	// 셋업
	heap.Init(j)
	sort.Slice(jobList, func(i, k int) bool {
		return jobList[i].startTime < jobList[k].startTime
	})
	time, totalTurnaround, count := 0, 0, 0
	n := len(jobs)
	jobIdx := 0
	for count < n {
		// 1. 현재 시간보다 시작 시간이 작은 작업들을 힙에 추가
		for jobIdx < n && jobList[jobIdx].startTime <= time {
			heap.Push(j, jobList[jobIdx])
			jobIdx++
		}
		// 2. 힙에서 작업을 꺼내서 실행
		if j.Len() > 0 {
			job := heap.Pop(j).(Job)
			time += job.runTime
			totalTurnaround += time - job.startTime
			count++
		} else {
			time = jobList[jobIdx].startTime
		}
	}
	return totalTurnaround / n
}
