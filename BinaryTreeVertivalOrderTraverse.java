package leetcode;

import java.util.*;

public class BinaryTreeVertivalOrderTraverse {
    public class Solution {
        public List<Integer> verticalOrder(TreeNode root) {
            List<Integer> sol = new ArrayList<>();
            //corner case
            if (root == null) {
                return sol;
            }
            Map<Integer, List<TreeNode>> map = new HashMap<>();
            Map<TreeNode, Integer> nodeCol = new HashMap<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            List<TreeNode> init = new ArrayList<>();
            init.add(root);
            map.put(0, init);
            nodeCol.put(root, 0);
            int minCol = 0, maxCol = 0;

            while (!q.isEmpty()) {
                TreeNode curNode = q.poll();
                int curCol = nodeCol.get(curNode);
                if (curNode.left != null) {
                    nodeCol.put(curNode.left, curCol - 1);
                    minCol = Math.min(minCol, curCol - 1);
                    q.offer(curNode.left);
                    if (map.containsKey(curCol - 1)) {
                        map.get(curCol - 1).add(curNode.left);
                    } else {
                        List<TreeNode> newLeftList = new ArrayList<>();
                        newLeftList.add(curNode.left);
                        map.put(curCol - 1, newLeftList);
                    }
                }
                if (curNode.right != null) {
                    nodeCol.put(curNode.right, curCol + 1);
                    maxCol = Math.max(maxCol, curCol + 1);
                    q.offer(curNode.right);
                    if (map.containsKey(curCol + 1)) {
                        map.get(curCol + 1).add(curNode.right);
                    } else {
                        List<TreeNode> newRightList = new ArrayList<>();
                        newRightList.add(curNode.right);
                        map.put(curCol + 1, newRightList);
                    }
                }
            }
            for (int i = minCol; i <= maxCol; i++) {
                List<TreeNode> curList = map.get(i);
                for (TreeNode curNode : curList) {
                    sol.add(curNode.key);
                }
            }
            return sol;
        }
    }

    public class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }
}