/*
6
Medium
*/

package laicode;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] sol = new StringBuilder[numRows];
        for (int i = 0; i < sol.length; i++) {
            sol[i] = new StringBuilder();
        }
        int step = 2 * (numRows - 1);
        for (int i = 0; i < s.length(); i++) {
            int index = i % step;
            if (index <= numRows - 1) {
                sol[index].append(s.charAt(i));
            } else {
                sol[step - index].append(s.charAt(i));
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : sol) {
            res.append(sb.toString());
        }
        return res.toString();
    }
}
