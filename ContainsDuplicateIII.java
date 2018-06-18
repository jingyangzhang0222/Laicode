/*
*
 Given an array of integers, find out whether there are two distinct indices i and j in
 the array such that the absolute difference between nums[i] and nums[j] is at most t and
 the absolute difference between i and j is at most k.
* */
package leetcode;

import java.util.TreeSet;

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int v) {
        // TreeSet
        TreeSet<Integer> treeset = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer ceil = treeset.ceiling(nums[i]);
            if (ceil != null && ceil - nums[i] <= v) {
                return true;
            }
            Integer floor = treeset.floor(nums[i]);
            if (floor != null && nums[i] - floor <= v) {
                return true;
            }
            treeset.add(nums[i]);
            if (i >= k) treeset.remove(nums[i - k]);
        }
        return false;
    }
}
