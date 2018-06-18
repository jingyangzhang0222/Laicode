/*
* Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

    20180526
    71
    medium
    ???
    O(n)
    O(n)
* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public String simplify(String path) {
        Deque<String> s = new ArrayDeque<>();
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !s.isEmpty()) {
                s.pollLast();
            } else if (!dir.equals("..") && !dir.equals(".") && !dir.equals("")) {
                s.offerLast(dir);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append('/');
            sb.append(s.pollFirst());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
