// Package pg42626 - [PG] 42626 - 더 맵게
// https://programmers.co.kr/learn/courses/30/lessons/42626
// 난이도: lv2
// 태그: heap
//
// 시간복잡도: O(N log N)
// 공간복잡도: O(N)
package pg42626

import "container/heap"

func Solution(scovile []int, K int) int {
	pq := NewPriorityQueue[int](scovile, func(a, b int) bool {
		return a < b
	})

	count := 0
	for pq.Len() > 1 && pq.Peek() < K {
		first := pq.Pop()
		second := pq.Pop()
		third := first + (second * 2)
		pq.Push(third)
		count++
	}

	if pq.Peek() >= K {
		return count
	}
	return -1
}

// 2. 제네릭 + 캡슐화 적용된 우선순위 큐
type QueueInterface[T any] interface {
	Push(val T)
	Pop() T
	Peek() T
	Len() int
}

// PriorityQueue는 사용자가 안전하게 쓸 있도록 노출된 래퍼(Wrapper) 구조체입니다.
type PriorityQueue[T any] struct {
	internal *internalHeap[T]
}

func (pq *PriorityQueue[T]) Push(val T) {
	heap.Push(pq.internal, val)
}

// 컴파일 타임 인터페이스 검증
var _ QueueInterface[int] = (*PriorityQueue[int])(nil)

func (pq *PriorityQueue[T]) Pop() T {
	return heap.Pop(pq.internal).(T)
}

func (pq *PriorityQueue[T]) Peek() T {
	return pq.internal.Peek()
}

func (pq *PriorityQueue[T]) Len() int {
	return pq.internal.Len()
}

func NewPriorityQueue[T any](data []T, less func(a, b T) bool) *PriorityQueue[T] {
	ih := &internalHeap[T]{
		data: data,
		less: less,
	}
	// 정렬
	heap.Init(ih)
	return &PriorityQueue[T]{internal: ih}
}

// 내부 힙 : 캡슐화 진행 소문자로 시작해서 외부 패키지나 사용자가 직접 접근 불가
type internalHeap[T any] struct {
	data []T
	less func(a, b T) bool
}

func (h *internalHeap[T]) Len() int {
	return len(h.data)
}

func (h *internalHeap[T]) Less(i, j int) bool {
	return h.less(h.data[i], h.data[j])
}

func (h *internalHeap[T]) Swap(i, j int) {
	h.data[i], h.data[j] = h.data[j], h.data[i]
}

func (h *internalHeap[T]) Push(x any) {
	h.data = append(h.data, x.(T))
}

func (h *internalHeap[T]) Pop() any {
	old := h.data
	n := len(old)
	x := old[n-1]
	h.data = old[0 : n-1]
	return x
}

func (h *internalHeap[T]) Peek() T {
	return h.data[0]
}
