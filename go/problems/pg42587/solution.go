// Package pg42587 - [PG] 42587 - 프로세스
// https://programmers.co.kr/learn/courses/30/lessons/42587
// 난이도: lv2
// 태그: stack,queue
//
// 시간복잡도: O(n)
// 공간복잡도: O(n)
package pg42587

import (
	"container/list"
	"sort"
)

func Solution(priorities []int, location int) int {
	return solution04(priorities, location)
}

func solution04(priorities []int, location int) int {
	type Data struct {
		p, idx int
	}
	n := len(priorities)
	// 딱 필요한 만큼만 공간 할당
	size := n + 1
	q := make([]Data, size)
	head, tail := 0, 0

	pop := func() Data {
		d := q[head]
		head = (head + 1) % size
		return d
	}

	push := func(d Data) {
		q[tail] = d
		tail = (tail + 1) % size
	}

	for i, p := range priorities {
		push(Data{p, i})
	}

	importSort := priorities
	sort.Slice(importSort, func(i, j int) bool {
		return importSort[i] > importSort[j]
	})

	count, pIdx := 0, 0
	for head != tail {
		curr := pop()

		if curr.p < importSort[pIdx] {
			push(curr)
		} else {
			count++
			if curr.idx == location {
				return count
			}
			pIdx++
		}
	}
	return count
}

func solution03(priorities []int, location int) int {
	type Data struct {
		p, idx int
	}
	q := list.New()
	for i, p := range priorities {
		q.PushBack(Data{p, i})
	}
	sort.Sort(sort.Reverse(sort.IntSlice(priorities)))

	count, pIdx := 0, 0
	for q.Len() > 0 {
		front := q.Front()
		if front == nil {
			return count
		}
		curr := front.Value.(Data)
		q.Remove(front)

		if curr.p < priorities[pIdx] {
			q.PushBack(curr)
		} else {
			count++
			if curr.idx == location {
				return count
			}
			pIdx++
		}
	}
	return count
}

func solution02(priorities []int, location int) int {
	type Data struct {
		p, idx int
	}

	q := make([]Data, 0, len(priorities))
	for i, p := range priorities {
		q = append(q, Data{p, i})
	}
	head := 0 // 맨 앞을 가리키는 포인터
	count := 0
	pIdx := 0
	sort.Sort(sort.Reverse(sort.IntSlice(priorities)))

	for head < len(q) {
		curr := q[head]
		head++

		if curr.p < priorities[pIdx] {
			q = append(q, curr)
		} else {
			count++
			if curr.idx == location {
				return count
			}
			pIdx++
		}
	}
	return count
}

func solution01(priorities []int, location int) int {
	q := NewQueue[Data]()
	for i, priority := range priorities {
		q.Push(Data{
			index:   i,
			element: priority,
		})
	}
	sort.Slice(priorities, func(i, j int) bool {
		return priorities[i] > priorities[j]
	})
	count := 0
	for _, priority := range priorities {
		for !q.IsEmpty() {
			popLeft := q.PopLeft()
			if popLeft.element < priority {
				q.Push(popLeft)
				continue
			}
			count++
			if popLeft.index == location {
				return count
			}
			break
		}
	}
	return count
}

type Queue[T any] interface {
	PopLeft() T
	Push(T)
	IsEmpty() bool
}

type queue[T any] struct {
	data []T
}

func (q *queue[T]) PopLeft() T {
	front := q.data[0]
	q.data = q.data[1:]
	return front
}

func (q *queue[T]) Push(t T) {
	q.data = append(q.data, t)
}

func (q *queue[T]) IsEmpty() bool {
	return len(q.data) == 0
}

type Data struct {
	element int
	index   int
}

func NewQueue[T any]() Queue[T] {
	return &queue[T]{}
}
