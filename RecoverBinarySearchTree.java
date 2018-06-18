/*
* Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?

    20180528
    99
    hard
    ??
    O(n)
    O(height)
* */
package leetcode;

public class RecoverBinarySearchTree {
    public TreeNode recover(TreeNode root) {
        TreeNode[] prev = {null};
        TreeNode[] res = {null, null};
        inOrder(root, prev, res);

        int tmp = res[0].key;
        res[0].key = res[1].key;
        res[1].key = tmp;

        return root;
    }
    private void inOrder(TreeNode root, TreeNode[] prev, TreeNode[] res) {
        //base case
        if (root == null) {
            return;
        }

        inOrder(root.left, prev, res);

        if (prev[0] != null && prev[0].key > root.key) {
            res[0] = res[0] == null ? prev[0] : res[0];
            res[1] = root;
        }

        prev[0] = root;
        inOrder(root.right, prev, res);
    }

    public void recoverTreeMorrisAlgorithms(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        // Morris Traversal
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    if(pre!=null && pre.key > root.key){
                        if(first==null){first = pre;second = root;}
                        else{second = root;}
                    }
                    pre = root;

                    temp.right = null;
                    root = root.right;
                }else{
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                if(pre!=null && pre.key > root.key){
                    if(first==null){first = pre;second = root;}
                    else{second = root;}
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if(first!= null && second != null){
            int t = first.key;
            first.key = second.key;
            second.key = t;
        }
    }
}
