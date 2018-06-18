package leetcode;

import java.util.*;

public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf test = new CountOfSmallerNumbersAfterSelf();
        int[] input = {1,7,3,2,9,2,1,1,1,1,5,4};
        List<Integer> res = test.countSmaller(input);
        for (int num : res) {
            System.out.print(num);
            System.out.print(' ');
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sol = new ArrayList<>();

        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (root == null) {
                root = new Node(nums[i]);
                sol.add(0);
                continue;
            }

            Node prev = null;
            Node cur = root;
            int smallerCount = 0;

            while (cur != null) {
                if (cur.val == nums[i]) {
                    cur.count++;
                    break;
                } else if (nums[i] > cur.val) {
                    smallerCount += cur.leftCount;
                    smallerCount += cur.count;
                    prev = cur;
                    cur = cur.right;
                } else {
                    cur.leftCount++;
                    prev = cur;
                    cur = cur.left;
                }
            }

            if (prev != null && cur == null) {
                if (nums[i] < prev.val) {
                    cur = new Node(nums[i]);
                    prev.left = cur;
                } else if (nums[i] > prev.val) {
                    cur = new Node(nums[i]);
                    prev.right = cur;
                }
            }

            sol.add(smallerCount);
        }
        Collections.reverse(sol);
        return sol;
    }
    class Node{
        Node left, right;
        int val, count, leftCount;
        public Node(int val) {
            this.val = val;
            count = 1;
            leftCount = 0;
        }
    }

    public List<Integer> countSmallerAnswer(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sorted.length; ++i)
            if (i == 0 || sorted[i] != sorted[i - 1])
                ranks.put(sorted[i], ++rank);

        FenwickTree tree = new FenwickTree(ranks.size());
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; --i) {
            int sum = tree.query(ranks.get(nums[i]) - 1);
            ans.add(tree.query(ranks.get(nums[i]) - 1));
            tree.update(ranks.get(nums[i]), 1);
        }

        Collections.reverse(ans);
        return ans;
    }
}
