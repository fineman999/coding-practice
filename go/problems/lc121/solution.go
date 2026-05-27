// Package lc121 - [LC] 121 - Best Time to Buy and Sell Stock
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// 난이도: easy
// 태그: array
//
// LeetCode 제출용 시그니처를 직접 작성하세요.
package lc121

func maxProfit(prices []int) int {
	if len(prices) == 0 {
		return 0
	}
	maxPrice := 0
	minPrice := prices[0]
	for i := 1; i < len(prices); i++ {
		if prices[i] < minPrice {
			minPrice = prices[i]
		}
		if prices[i]-minPrice > maxPrice {
			maxPrice = prices[i] - minPrice
		}
	}
	return maxPrice
}
