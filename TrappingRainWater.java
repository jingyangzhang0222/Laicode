/*
* Given n non-negative integers representing an elevation map where the width of each bar is 1,
* compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.



The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

    O(n)
    O(1)
* */

package laicode;

public class TrappingRainWater {
    public int trapWater(int[] A) {
  	/*
    	We only care about leftMax at i, and rightMax at i
      trade space with time, and time counld be optimized
    */
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = A.length - 1;

        int sum = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, A[left]);
            rightMax = Math.max(rightMax, A[right]);
            if (leftMax < rightMax) {
                sum += leftMax - A[left++];
            } else {
                sum += rightMax - A[right--];
            }
        }
        return sum;
    }

    public int trapWaterNaive(int[] A) {
        // O(n ^ 2)
        // O(1)
        if (A == null || A.length < 3) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < A.length - 1; i++) {
            int left = i - 1;
            int leftMax = 0;
            int right = i + 1;
            int rightMax = 0;
            while (left >= 0) {
                leftMax = Math.max(leftMax, A[left--]);
            }
            while (right < A.length) {
                rightMax = Math.max(rightMax, A[right++]);
            }
            if (leftMax <= A[i] || rightMax <= A[i]) {
                continue;
            }
            sum += Math.min(leftMax, rightMax) - A[i];
        }
        return sum;
    }
}
