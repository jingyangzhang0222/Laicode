/*
* Given three sorted integer arrays, pick one element from each of them, what is the min value of |x - y| + |y - z| + |z - x|.

Assumptions:

The given three arrays are not null or empty.
Examples:

a = {1, 2, 3}

b = {4, 5}

c = {3, 4}

The minimum value is |3 - 4| + |4 - 4| + |4 - 3| = 2
* */
package leetcode;

public class MinimumThreeArrayDistance {public int minDistance(int[] a, int[] b, int[] c) {
    // 2 * (max - min)
    int globalMin = Integer.MAX_VALUE;
    int idx1 = 0, idx2 = 0, idx3 = 0;
    while (idx1 < a.length && idx2 < b.length && idx3 < c.length) {
        globalMin = Math.min(globalMin, 2 * (Math.max(a[idx1], Math.max(b[idx2], c[idx3])) - Math.min(a[idx1], Math.min(b[idx2], c[idx3]))));
        if (a[idx1] <= b[idx2] && a[idx1] <= c[idx3]) {
            idx1++;
        } else if (b[idx2] <= a[idx1] && b[idx2] <= c[idx3]) {
            idx2++;
        } else {
            idx3++;
        }
    }
    return globalMin;
}
}
//O(len1 + len2 + len3)