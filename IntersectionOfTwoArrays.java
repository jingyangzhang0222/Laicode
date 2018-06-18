package leetcode;

import java.util.*;

public class IntersectionOfTwoArrays {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> sol = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int fre = map.get(num);
                map.put(num, fre + 1);
            }
        }
        for (int num : nums2) {
            if (map.containsKey(num)) {
                sol.add(num);
                int fre = map.get(num);
                if (fre == 1) {
                    map.remove(fre);
                } else {
                    map.put(num, fre - 1);
                }
            }
        }
        return toIntArray(sol.toArray(new Integer[0]));
    }
    public int[] intersectSort(int[] nums1, int[] nums2) {
        List<Integer> sol = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int idx1 = 0, idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1[idx1] < nums2[idx2]) {
                idx1++;
            } else if (nums2[idx2] < nums1[idx1]) {
                idx2++;
            } else {
                sol.add(nums1[idx1++]);
                idx2++;
            }
        }
        return toIntArray(sol.toArray(new Integer[0]));
    }
    private int[] toIntArray(Integer[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
}
