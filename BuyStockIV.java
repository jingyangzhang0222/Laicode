/*
 * 20180516
 * 188
 * hard
 * DP, transaction function
 * O(nk)
 * O(k)
 *
 * */
package laicode;

import java.util.Arrays;

public class BuyStockIV {
    public int maxProfit(int k, int[] prices) {
        if (k > prices.length / 2) {
            return buyStockII(prices);
        }
        int[] hold = new int[k + 1], sold = new int[k + 1];
        Arrays.fill(hold, Integer.MIN_VALUE);///
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                hold[i] = Math.max(hold[i], sold[i - 1] - price);
                sold[i] = Math.max(sold[i], hold[i] + price);
            }
        }
        return sold[k];
    }

    private int buyStockII(int[] array) {
        int totalPro = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] > array[i]) {
                totalPro += array[i + 1] - array[i];
            }
        }
        return totalPro;
    }
}
