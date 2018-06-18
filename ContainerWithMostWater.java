/*
11
easy

  0 1 2 3 4 5 6
  2 1 3 5 6 3 2
          |
        | |
        | |
      | | | |
  |   | | | | |
  | | | | | | |
*/

package laicode;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int globalMax = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            globalMax = Math.max(Math.min(height[left], height[right]) * (right - left), globalMax);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return globalMax;
    }
}
