package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ABCD {
    public boolean exist(int[] array) {
        Arrays.sort(array);
        Map<Integer, int[]> map = new HashMap<>();
        for (int ac = 0; ac < array.length - 1; ac++) {
            for (int bd = ac + 1; bd < array.length; bd++) {
                if (map.containsKey(array[bd] - array[ac]) && map.get(array[bd] - array[ac])[1] < ac) {
                    return true;
                }
                if (!map.containsKey(array[bd] + array[ac])) {
                    int[] pairs = {ac, bd};
                    map.put(array[ac] + array[bd], pairs);
                }
            }
        }
        return false;
    }
    //O(n ^ 2)
    //O(n ^ 2)

    public boolean exist1(int[] array) {
        Arrays.sort(array);
        for (int d = array.length - 1; d >= 3; d--) {
            for (int a = 0; a < d - 2; a++) {
                int b = a + 1;
                int c = d - 1;
                while (b < c) {
                    int fakeD = array[a] + array[b] + array[c];
                    if (fakeD == array[d]) {
                        return true;
                    }
                    if (fakeD < array[d]) {
                        b++;
                    } else {
                        c--;
                    }
                }
            }
        }
        return false;
    }
}
//O(n ^ 3)
//O(1)