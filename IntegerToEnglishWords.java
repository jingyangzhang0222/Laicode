/*
* Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,

123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

    O(1)
    O(n)
* */
package leetcode;

public class IntegerToEnglishWords {
    private static final String[] coma = {"Thousand", "Million", "Billion"};
    private static final String[] deci = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety", "Hundred"};
    private static final String[] fund = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen"};
    public static void main(String[] args) {
        IntegerToEnglishWords test = new IntegerToEnglishWords();
        System.out.println(test.numberToWords(2000010000));
    }
    public String numberToWords(int num) {
        String input = ((Integer)num).toString();
        if (input.equals("0")) return "Zero";
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i -= 3) {
            int right = i + 1;
            int left = Math.max(0, i - 2);
            String prev = sb.toString();
            sb = new StringBuilder();
            String cur = helper(input.substring(left, right), index++);
            sb.append(cur);
            sb.append(prev);
            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }
    private String helper(String number, int index) {
        int one = number.charAt(number.length() - 1) - '0';
        int ten = number.length() >= 2 ? number.charAt(number.length() - 2) - '0' : 0;
        int hundred = number.length() == 3 ? number.charAt(0) - '0' : 0;
        StringBuilder sb = new StringBuilder();

        if (hundred != 0) {
            sb.append(fund[hundred]);
            sb.append(' ');
            sb.append("Hundred");
            sb.append(' ');
        }

        if (ten >= 2) {
            sb.append(deci[ten - 2]);
            sb.append(' ');
        } else if (ten == 1) {
            one = 10 + one;
        }

        if (one != 0) {
            sb.append(fund[one]);
            sb.append(' ');
        }

        if (sb.length() == 0) {
            return "";
        }

        if (index > 0) {
            sb.append(coma[index - 1]);
            sb.append(' ');
        }

        return sb.toString();
    }
}
