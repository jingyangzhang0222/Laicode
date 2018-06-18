package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public List<String> findMissingRanges(int[] num, int lower, int upper) {
        List<String> sol = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length; i++) {
            if (num[i] != lower) {
                sb.append(lower);
                if (num[i] != lower + 1) {
                    sb.append("->");
                    sb.append(num[i] - 1);
                }
                sol.add(sb.toString());
                sb = new StringBuilder();
            }
            lower = num[i] + 1;
        }
        if (lower == upper) {
            sb.append(upper);
            sol.add(sb.toString());
        } else if (lower < upper && lower != Integer.MIN_VALUE) {
            sb.append(lower);
            sb.append("->");
            sb.append(upper);
            sol.add(sb.toString());
        }
        return sol;
    }
}