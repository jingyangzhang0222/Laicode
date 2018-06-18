/*
  20180516
  309
  medium
  DP
  O(n)
  O(1)
  (optimal)
                     rest  _
                  /  |_|  |\
            buy  /  	    \   rest
               |/_           \
             hold   ----->   sold
             |_|     sell
*/

package leetcode;

public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        //corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int hold = Integer.MIN_VALUE, rest = 0, sold = 0;
        for (int price : prices) {
            int prevHold = hold;
            hold = Math.max(prevHold, rest - price);
            rest = Math.max(rest, sold);
            sold = prevHold + price;
        }
        return Math.max(sold, rest);
    }
}
