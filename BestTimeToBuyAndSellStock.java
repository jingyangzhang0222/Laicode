/*
 * 20180516
 * 121
 * easy
 * DP -> greedy
 * O(n)
 * O(1)
 * */
package leetcode;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int globalMax = 0, subMax = 0, min = Integer.MAX_VALUE;
        for (int price : prices) {
            min = Math.min(min, price);
            subMax = Math.max(subMax, price - min);
            globalMax = Math.max(globalMax, subMax);
        }
        return globalMax;
    }
}
