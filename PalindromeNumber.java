/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Could you solve it without converting the integer to a string?

* 20180520
* 9
* easy
* math
* O(logn)
* O(1)
* */
package leetcode;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        //compare half of the integer
        //1 2 3 4 3 2 1 -> 1 2 3 4, 1 2 3
        //1 2 3 3 2 1 -> 1 2 3, 1 2 3
        int tran = 0;
        while (tran < x) {
            tran = 10 * tran + x % 10;
            x /= 10;
        }
        if (x == tran) {
            return true;
        }
        tran /= 10;
        return tran == x;
    }
}
