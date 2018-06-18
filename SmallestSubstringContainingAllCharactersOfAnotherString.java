/*
* Given two strings s1 and s2, find the shortest substring in s1 containing all characters in s2.

If there does not exist such substring in s1, return an empty string.

Assumptions:

s1 and s2 are not null or empty.
Examples:

s1= “The given test strings”

s2: “itsst”

Output string: “st stri”

    O(n + m)
    O(1)
* */
package leetcode;

public class SmallestSubstringContainingAllCharactersOfAnotherString {
    public String smallest(String s1, String s2) {
        int[] map2 = new int[256];
        int typeCount2 = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (map2[s2.charAt(i)] == 0) typeCount2++;
            map2[s2.charAt(i)]++;
        }

        int globalMin = Integer.MAX_VALUE;
        int s = 0, typeCount1 = 0, start = s;
        int[] map1 = new int[256];
        for (int f = 0; f < s1.length(); f++) {
            map1[s1.charAt(f)]++;
            if (map1[s1.charAt(f)] == map2[s1.charAt(f)]) {
                typeCount1++;
            }

            while (typeCount1 == typeCount2) {
                if (f - s + 1 < globalMin) {
                    globalMin = f - s + 1;
                    start = s;
                }
                map1[s1.charAt(s)]--;
                if (map2[s1.charAt(s)] != 0 && map1[s1.charAt(s)] < map2[s1.charAt(s)]) {//////////////
                    typeCount1--;
                }
                s++;
            }
        }
        return globalMin == Integer.MAX_VALUE ? "" : s1.substring(start, start + globalMin);
    }
}
