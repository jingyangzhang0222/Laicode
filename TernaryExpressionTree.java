/*
* Implement a solution to parse a ternary expression into a tree.

Assumptions:

The input ternary expression is a string, and it is guaranteed to be valid.
Examples:

a?b:c  -->

   a

 /   \

b     c

a?b?c:d:e  -->

      a

    /    \

  b       e

/    \

c    d


* */
package leetcode;

public class TernaryExpressionTree {
    public ExpNode tree(String exp) {
        //corner case
        if (exp == null || exp.equals("")) {
            return null;
        }

        return helper(exp, new int[1]);
    }
    private ExpNode helper(String exp, int[] start) {
        //base case
        if (start[0] == exp.length()) {
            return null;
        }

        ExpNode root = new ExpNode(exp.charAt(start[0]));

        if (start[0] + 1 < exp.length() && exp.charAt(start[0] + 1) == '?') {//has left child
            start[0] += 2;
            root.left = helper(exp, start);
            //if left child exists, then right child must exist
            root.right = helper(exp, start);
        } else {
            //else: it should be a leaf node
            start[0] += 2;
        }

        return root;
    }
}
