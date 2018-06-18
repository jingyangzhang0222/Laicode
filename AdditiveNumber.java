/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199.
             1 + 99 = 100, 99 + 100 = 199
    20180523
    306
    medium
    DFS
    O()
    O()
* */
package laicode;

public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if (num.length() <= 2) {
            return false;
        }
        boolean[] found = {false};
        DFS(num, 1, 0, found, new long[2]);
        return found[0];
    }
    private void DFS(String num, int index, int start, boolean[] found, long[] nums) {
        //base case
        if (found[0]) {
            return;
        }
        if (start == num.length()) {
            found[0] = index > 3;
            return;
        }

        long numberAtThisLevel = 0;///////////////////////
        if (index < 3) {
            for (int i = start; i < num.length(); i++) {
                numberAtThisLevel = 10 * numberAtThisLevel + num.charAt(i) - '0';
                nums[index - 1] = numberAtThisLevel;
                DFS(num, index + 1, i + 1, found, nums);
                nums[index - 1] = 0;
                if (i == start && num.charAt(i) == '0') break;///////////////////////////
            }
        } else {
            for (int i = start; i < num.length(); i++) {
                numberAtThisLevel = 10 * numberAtThisLevel + num.charAt(i) - '0';
                if (numberAtThisLevel < nums[0] + nums[1]) {
                    if (i == start && num.charAt(i) == '0') break;/////////////////////////
                    continue;
                } else if (numberAtThisLevel > nums[0] + nums[1]) {
                    return;
                } else { // numberAtThisLevel == nums[0] + nums[1]
                    long tmp = nums[0];
                    nums[0] = nums[1];
                    nums[1] = numberAtThisLevel;
                    DFS(num, index + 1, i + 1, found, nums);
                    nums[1] = nums[0];//////////////////////////////////////
                    nums[0] = tmp;///////////////////////////////////////////
                }
            }
        }
    }
}
