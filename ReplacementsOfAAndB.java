package leetcode;

public class ReplacementsOfAAndB {
    public static void main (String[] args) {
        String input = "ab";
        ReplacementsOfAAndB test = new ReplacementsOfAAndB();
        System.out.print(test.minReplacements(input));
    }
    public int minReplacements(String input) {
        //  0 1 2 3 4 5 6 7 8 9
        //  a b a b a a a a b a
        // 0 0 1 1 2 2 2 2 2 3 3 bCOunt
        // 7 6 6 5 5 4 3 2 1 1 0 aCount
        // 0 1 2 3 4 5 6 7 8 9 10
        int[] aCount = new int[input.length() + 1];
        int[] bCount = new int[input.length() + 1];
        for (int i = 1; i < bCount.length; i++) {
            int j = aCount.length - 1 - i;
            bCount[i] = input.charAt(i - 1) == 'b' ? bCount[i - 1] + 1 : bCount[i - 1];
            aCount[j] = input.charAt(j) == 'a' ? aCount[j + 1] + 1 : aCount[j + 1];
        }
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < bCount.length; i++) {
            globalMin = Math.min(globalMin, bCount[i] + aCount[i]);
        }
        return globalMin;
    }
}
//O(n), O(n)
/*
		if (input == "ba") return 1;
    if (input == "aabbbaba") return 2;
    if (input == "abaabb") return 1;
    if (input == "bbbbbaaaab") return 4;
    if (input == "aaabbabbaaaabba") return 5;
    if (input == "bbaaabaabbbb") return 3;
    if (input == "abbbbbbabab") return 2;
    if (input == "bbbbbaaaa") return 4;
    if (input == "aaaaaabbbbab") return 1;
    if (input == "aaaaabbbbaaaaa") return 4;
    if (input == "ab") return 0;
    if (input == "a") return 0;
    if (input == "") return 0;
    if (input == "b") return 0;
    if (input == "aaaaaaaa") return 0;
    if (input == "bb") return 0;
    if (input == "bbbbbb") return 0;
*/