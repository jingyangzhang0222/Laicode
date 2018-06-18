package leetcode;

import java.util.*;

public class TwoSumClosest {
    public List<Integer> closest(int[] array, int target) {

        int[] oneD = {1,1,1,1,1};
        int[][] test = new int[5][5];
        test[0] = oneD;

        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        int globalMinDiff = Integer.MAX_VALUE;
        int[] res = new int[2];
        List<Integer> sol = new ArrayList<>();
        while (left < right) {///////////////////////////////
            if (Math.abs(array[left] + array[right] - target) > globalMinDiff) {
                break;
            } else {
                res[0] = array[left];
                res[1] = array[right];
                globalMinDiff = Math.abs(array[left] + array[right] - target);
            }
            if (array[left] + array[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        sol.add(res[0]);
        sol.add(res[1]);
        return sol;
    }
}
