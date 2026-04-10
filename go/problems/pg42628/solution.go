// Package pg42628 - [PG] 42628 - 이중우선순위큐
// https://programmers.co.kr/learn/courses/30/lessons/42628
// 난이도: lv3
// 태그: heap
//
// 시간복잡도: O(NLogN)
// 공간복잡도: O(N)
package pg42628

import (
	"container/heap"
	"strconv"
	"strings"
)

type Item struct {
	number int
	index  int
}
type PriorityQueue struct {
	items []Item
	less  func(item1, item2 Item) bool
}

func (p *PriorityQueue) Len() int {
	return len(p.items)
}

func (p *PriorityQueue) Less(i, j int) bool {
	return p.less(p.items[i], p.items[j])
}

func (p *PriorityQueue) Swap(i, j int) {
	p.items[i], p.items[j] = p.items[j], p.items[i]
}

func (p *PriorityQueue) Push(x any) {
	p.items = append(p.items, x.(Item))
}

func (p *PriorityQueue) Pop() any {
	n := len(p.items)
	item := p.items[n-1]
	p.items[n-1] = Item{}
	p.items = p.items[:n-1]
	return item
}
func (p *PriorityQueue) Peek() Item {
	return p.items[0]
}

func Solution(operations []string) []int {
	minHeap := &PriorityQueue{
		less: func(a, b Item) bool {
			return a.number < b.number
		},
		items: make([]Item, 0),
	}
	maxHeap := &PriorityQueue{
		less: func(a, b Item) bool {
			return a.number > b.number
		},
		items: make([]Item, 0),
	}
	deletedSet := make(map[int]bool)

	for i := range operations {
		split := strings.Split(operations[i], " ")
		op := split[0]
		num, _ := strconv.Atoi(split[1])
		if op == "I" {
			heap.Push(minHeap, Item{num, i})
			heap.Push(maxHeap, Item{num, i})
		} else {
			if num == 1 {
				syncSetDeleted(maxHeap, deletedSet)
				if maxHeap.Len() > 0 {
					item := heap.Pop(maxHeap).(Item)
					deletedSet[item.index] = true
				}
			} else {
				syncSetDeleted(minHeap, deletedSet)
				if minHeap.Len() > 0 {
					item := heap.Pop(minHeap).(Item)
					deletedSet[item.index] = true
				}
			}
		}
	}

	syncSetDeleted(minHeap, deletedSet)
	syncSetDeleted(maxHeap, deletedSet)
	if minHeap.Len() == 0 || maxHeap.Len() == 0 {
		return []int{0, 0}
	}
	return []int{maxHeap.Peek().number, minHeap.Peek().number}
}

func syncSetDeleted(queue *PriorityQueue, deletedSet map[int]bool) {
	for queue.Len() > 0 && deletedSet[queue.Peek().index] {
		heap.Pop(queue)
	}
}
