/*
* 20180516
* 122
* easy
* DP -> greedy
* O(n)
* O(1)
* */

package leetcode;

public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        int totalPro = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                totalPro += prices[i + 1] - prices[i];
            }
        }
        return totalPro;
    }
}
