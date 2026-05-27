"""
[LC] 121 - Best Time to Buy and Sell Stock
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
난이도: easy
태그: array

LeetCode 제출용 시그니처를 직접 작성하세요.
"""
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        sort_prices = [(prices[i], i) for i in range(len(prices))]
        sort_prices.sort(reverse=True)
        right = 0
        answer = 0
        for i in range(len(prices)):
            while right < len(sort_prices) and sort_prices[right][1] <= i:
                right+=1
            if prices[i] > sort_prices[right][0]:
                continue
            answer = max(answer, sort_prices[right][0]-prices[i])

        return answer
