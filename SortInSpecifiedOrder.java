package leetcode;

import java.util.*;

public class SortInSpecifiedOrder {
    public int[] sortSpecial(int[] A1, int[] A2) {
        Integer[] helper = toIntegerArray(A1);
        Arrays.sort(helper, new MyComparator(A2));
        return toIntArray(helper);
    }

    private int[] toIntArray(Integer[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }

    private Integer[] toIntegerArray(int[] array) {
        Integer[] res = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }

    static class MyComparator implements Comparator<Integer> {
        private Map<Integer, Integer> map;

        public MyComparator(int[] reference) {
            map = new HashMap<>();
            for (int i = 0; i < reference.length; i++) {
                map.put(reference[i], i);
            }
        }

        @Override
        public int compare(Integer num1, Integer num2) {
            Integer index1 = map.get(num1);
            Integer index2 = map.get(num2);
            if (index1 == null && index2 == null) {
                return num1.compareTo(num2);
            } else if (index1 != null && index2 != null) {
                return index1.compareTo(index2);
            }
            return index1 == null ? 1 : -1;
        }
    }
}