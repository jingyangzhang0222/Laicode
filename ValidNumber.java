/*
*
*
* Validate if a given string is numeric.

Input:  “0”    Output: true

Input:  “ 0.1 ” Output: true

Input:  “abc”   Output: false

Input:  “1 a”   Output: false

Input:  “2e10”    Output: true
* */
package laicode;

public class ValidNumber {
    public boolean isNumber(String s) {
        //corner case
        if (s== null || s.length() == 0) {
            return false;
        }
        int start = 0;
        while (start < s.length() - 1 && s.charAt(start) == ' ') {
            start++;
        }
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0 || start >= s.length() || !isNumber(s.charAt(end))) {
            return false;
        }
        int eCount = 0;
        int dotCount = 0;
        int plusCount = 0;
        int minusCount = 0;

        for (int i = start; i <= end; i++) {
            char ele = s.charAt(i);
            if (!validInput(ele)) {
                return false;
            }

            if (ele == 101 || ele == 69) {//e OR E
                if (i == 0 || eCount != 0 ||
                        (!isSign(s.charAt(i + 1)) && !isNumber(s.charAt(i + 1))) ||
                        !isNumber(s.charAt(i - 1))) {
                    return false;
                } else {
                    eCount = 1;
                }
            }

            if (ele == 46) {//.
                if (dotCount != 0 || eCount == 1 || !isNumber(s.charAt(i + 1)) ||
                        (i != 0 && !isNumber(s.charAt(i - 1)) && !isSign(s.charAt(i - 1)))) {
                    return false;
                } else {
                    dotCount = 1;
                }
            }

            if (ele == 43) {//+
                if (plusCount >= 2 || !isNumber(s.charAt(i + 1)) ||
                        (i != 0 && !isE(s.charAt(i - 1)))) {
                    return false;
                } else {
                    plusCount++;
                }
            }

            if (ele == 45) {//-
                if (minusCount >= 2 || !isNumber(s.charAt(i + 1)) ||
                        (i != 0 && !isE(s.charAt(i - 1)))) {
                    return false;
                } else {
                    minusCount++;
                }
            }
        }
        return true;
    }
    private boolean validInput(char ele) {
        return (48 <= ele && ele <= 57) || ele == 46 ||
                ele == 101 || ele == 69 || ele == 43 || ele == 45;
    }
    private boolean isNumber(char ele) {
        return ele >= 48 && ele <= 57;
    }
    private boolean isSign(char ele) {
        return ele == 45 || ele == 43;
    }
    private boolean isE(char ele) {
        return ele == 69 || ele == 101;
    }
}
