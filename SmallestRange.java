package leetcode;

import java.util.PriorityQueue;

public class SmallestRange {
    public static void main(String[] args) {
        int[][] test = {{4, 10, 11}, {1, 3, 7, 11}, {6, 9, 16}, {4, 10}};
        SmallestRange zjy = new SmallestRange();
        int[] res = zjy.smallestRange(test);
        for (int num : res) {
            System.out.println(num);
        }
    }

    public int[] smallestRange(int[][] arrays) {
        int[] sol = new int[2];
        int minRange = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        PriorityQueue<Element> pq = new PriorityQueue<>();

        for (int i = 0; i < arrays.length; i++) {
            min = Math.min(min, arrays[i][0]);
            max = Math.max(max, arrays[i][0]);
            pq.offer(new Element(arrays[i][0], i, 0));
        }
        minRange = Math.min(minRange, max - min);
        sol[0] = min;
        sol[1] = max;

        while (true) {
            Element ele = pq.poll();
            if (ele.y == arrays[ele.x].length - 1) {
                break;
            }
            pq.offer(new Element(arrays[ele.x][ele.y + 1], ele.x, ele.y + 1));
            max = Math.max(max, arrays[ele.x][ele.y + 1]);
            min = pq.peek().val;////////////////////min = Math.min(min, pq.peek().val)

            if (max - min < minRange) {
                minRange = max - min;
                sol[0] = min;
                sol[1] = max;
            }
        }
        return sol;
    }

    class Element implements Comparable<Element> {
        int val;
        int x;
        int y;

        public Element(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Element ele) {
            if (this.val == ele.val) {
                return 0;
            }
            return this.val < ele.val ? -1 : 1;
        }
    }
}