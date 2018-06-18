/*
* One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

    O(n)
    O(1)
* */
package leetcode;

public class VerifyPreorderSerializationOfABinaryTree {
    public static void main(String[] args) {
        VerifyPreorderSerializationOfABinaryTree test = new VerifyPreorderSerializationOfABinaryTree();
        System.out.println(test.isValidSerializationRecursion("9,#,#,1"));
    }

    public boolean isValidSerializationIterative(String preorder) {
        int index = 0, bubble = 1;
        while (index < preorder.length()) {
            if (preorder.charAt(index) == ',') {
                index++;
            } else if (preorder.charAt(index) == '#') {
                bubble--;
                index++;
            } else {
                bubble++;
                while (index < preorder.length() && Character.isDigit(preorder.charAt(index))) {
                    index++;
                }
            }
        }
        return bubble == 0;
    }

    public boolean isValidSerializationRecursion(String preorder) {
        String[] nodes = preorder.split(",");
        int[] bubble = new int[]{1};
        int[] rootIndex = new int[1];
        helper(nodes, rootIndex, bubble);
        return bubble[0] == 0 && rootIndex[0] == nodes.length;
    }

    private boolean helper(String[] nodes, int[] rootIndex, int[] bubble) {
        //base case
        if (bubble[0] <= 0) {
            return false;
        }
        if (rootIndex[0] >= nodes.length) {
            return true;
        }

        if (nodes[rootIndex[0]].charAt(0) == '#') {
            bubble[0]--;
            rootIndex[0]++;
            return true;
        } else {
            bubble[0]++;
            rootIndex[0]++;
            boolean leftIsValid = helper(nodes, rootIndex, bubble);
            if (!leftIsValid) return false;
            boolean rightIsValid = helper(nodes, rootIndex, bubble);
            if (!rightIsValid) return false;
        }
        return true;
    }
}