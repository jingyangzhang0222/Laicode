package laicode;

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        // 1 2 3 4 3 2 4  4 2 354 3 2 1
        //         l           r
        boolean deleted = false;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if(deleted) {
                    return false;
                } else {
                    if (s.charAt(right - 1) == s.charAt(left)) {
                        right--;
                        deleted = true;
                    } else if (s.charAt(left + 1) == s.charAt(right)) {
                        left++;
                        deleted = true;
                    } else {
                        return false;
                    }
                }
            }
            left++;
            right--;
        }
        return true;
    }
}
