/*
* An equal sum partition of a sequence of numbers is a grouping of the numbers (in the same order as the original sequence) in such a way that each group has the same sum. For example, the sequence:

2 5 1 3 3 7

may be grouped as:

(2 5) (1 3 3) (7)

to yield an equal sum of 7.



Note: The partition that puts all the numbers in a single group is an equal sum partition with the sum equal to the sum of all the numbers in the sequence.



For this problem, you will write a program that takes as input a sequence of positive integers and returns the smallest sum for an equal sum partition of the sequence.



Input: A decimal integer N (1 ≤ N ≤ 10000), giving the total number of integers in the array, and the array X of positive decimal integers.


Output: the smallest sum for an equal sum partition of the sequence.
* */
package leetcode;

public class EqualSumPartitions {
    public int getMinEqualSumPartition(int N, int[] X) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : X) {
            sum += num;
            max = Math.max(max, num);
        }
        for (int i = max; i < sum; i++) {
            if (sum % i == 0) {
                int subsum = 0;
                int j = 0;
                while (j < X.length) {
                    subsum += X[j++];
                    if (subsum > i) break;
                    subsum %= i;
                }
                if (j == X.length) return i;
            }
        }
        return sum;
    }
}
