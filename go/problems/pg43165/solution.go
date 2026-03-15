// Package pg43165 - [PG] 43165 - 타겟 넘버
// https://programmers.co.kr/learn/courses/30/lessons/43165
// 난이도: lv2
// 태그: dfs,bfs
//
// 시간복잡도: O(?)
// 공간복잡도: O(?)
package pg43165

func Solution(numbers []int, target int) int {
	//return solution01(numbers, target)

	return solution02(numbers, target)
}

func solution02(numbers []int, target int) int {
	currenSums := []int{0}

	for _, num := range numbers {
		// 미리 크기 할당
		nextSums := make([]int, 0, len(currenSums)*2)
		for _, sum := range currenSums {
			nextSums = append(nextSums, sum+num)
			nextSums = append(nextSums, sum-num)
		}
		currenSums = nextSums
	}
	count := 0
	for _, sum := range currenSums {
		if sum == target {
			count++
		}
	}
	return count
}

func solution01(numbers []int, target int) int {
	queue := NewQueue[int]()

	queue.Push(0)
	for i := range numbers {
		tempQueue := NewQueue[int]()
		for !queue.IsEmpty() {
			element := queue.PopLeft()
			tempQueue.Push(element + numbers[i])
			tempQueue.Push(element - numbers[i])
		}
		queue = tempQueue
	}
	count := 0
	for !queue.IsEmpty() {
		element := queue.PopLeft()
		if element == target {
			count++
		}
	}
	return count
}

func NewQueue[T any]() QueueInterface[T] {
	return &Queue[T]{}
}

type QueueInterface[T any] interface {
	Push(val T)
	PopLeft() T
	IsEmpty() bool
}

type Queue[T any] struct {
	elements []T
}

func (q *Queue[T]) IsEmpty() bool {
	return len(q.elements) == 0
}

func (q *Queue[T]) Push(val T) {
	q.elements = append(q.elements, val)
}

func (q *Queue[T]) PopLeft() T {
	front := q.elements[0]
	q.elements = q.elements[1:]
	return front
}
