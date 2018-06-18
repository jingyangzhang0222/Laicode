/*
* 20180515
* 832
* easy
* direct
* O(n ^ 2)
* O(1)
* */

package leetcode;

public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] row : A) {
            int left = 0;
            int right = row.length - 1;
            while (left < right) {
                int tmp = row[left];
                row[left++] = row[right] == 0 ? 1 : 0;
                row[right--] = tmp == 0 ? 1 : 0;
            }
            if (left == right) row[left] = row[left] == 1 ? 0 : 1;
        }
        return A;
    }
}
