/*
* Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
    20180525
    611
    medium
    greedy
    O(n ^ 2)
    O(1)
* */
package laicode;

import java.util.Arrays;

public class ValidTriangleNumber {
    public int numOfTriangles(int[] array) {
        Arrays.sort(array);
        int count = 0;
        for (int c = array.length - 1; c > 1; c--) {
            int a = 0, b = c - 1;
            while (a < b) {
                if (array[a] + array[b] > array[c]) {
                    count += b - a;
                    b--;
                } else {
                    a++;
                }
            }
        }
        return count;
    }
}
