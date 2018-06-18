/*
* Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
    20180523
    93
    medium
    ??
    O()
    O()
*
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public List<String> Restore(String ip) {
        List<String> sol = new ArrayList<>();
        if (ip == null || ip.length() <= 3) {
            return sol;
        }
        StringBuilder sb = new StringBuilder();
        for (int len1 = 1; len1 <= 3; len1++) {
            int start1 = 0;
            int end1 = start1 + len1 - 1;
            int start2 = end1 + 1;

            for (int len2 = 1; len2 <= 3 && start2 + len2 - 1 <= ip.length() - 3; len2++) {
                int end2 = start2 + len2 - 1;
                int start3 = end2 + 1;

                for (int len3 = 1; len3 <= 3 && start3 + len3 - 1 <= ip.length() - 2; len3++) {
                    int end3 = start3 + len3 - 1;

                    int len4 = ip.length() - len1 - len2 - len3;
                    int start4 = end3 + 1;
                    int end4 = ip.length() - 1;
                    if (len4 > 3) {
                        continue;
                    }
                    if (len4 < 0) {
                        break;
                    }

                    int ip1 = toNum(ip, start1, end1);
                    int ip2 = toNum(ip, start2, end2);
                    int ip3 = toNum(ip, start3, end3);
                    int ip4 = toNum(ip, start4, end4);
                    if (ip1 >= 0 && ip1 <= 255 && ip2 >= 0 && ip2 <= 255 && ip3 >= 0 && ip3 <= 255 && ip4 >= 0 && ip4 <= 255 && !(len4 != 1 && ip.charAt(start4) == '0')) {
                        sb.append(ip1);
                        sb.append('.');
                        sb.append(ip2);
                        sb.append('.');
                        sb.append(ip3);
                        sb.append('.');
                        sb.append(ip4);
                        sol.add(sb.toString());
                        sb = new StringBuilder();
                    }
                    if (ip.charAt(start3) == '0') {/////////////////
                        break;
                    }
                }
                if (ip.charAt(start2) == '0') {////////////////
                    break;
                }
            }
            if (ip.charAt(start1) == '0') {////////////////////
                break;
            }
        }
        return sol;
    }
    private int toNum(String ip, int start, int end) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            res = 10 * res + ip.charAt(i) - '0';
        }
        return res;
    }
}
