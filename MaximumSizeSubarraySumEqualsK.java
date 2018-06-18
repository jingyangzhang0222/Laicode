package leetcode;

import java.util.*;

public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //prefix
        //<sum, index>
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int globalMax = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                globalMax = i + 1;
            } else if (map.containsKey(sum - k)) {//sum - presum = k
                globalMax = Math.max(globalMax, i - map.get(sum - k));
            }
            //for the same sum, add the pair of <sum, index> only with the minimum index to map
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return globalMax;
    }
}
