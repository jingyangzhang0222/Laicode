/*
* Given a gym with k pieces of equipment without any obstacles.  Let’s say we bought a chair and wanted to put this chair into the gym such that the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ' ' denotes a cell without equipment. The cost of moving from one cell to its neighbor(left, right, up, down) is 1. You can put chair on any cell in the gym.

Assumptions

There is at least one equipment in the gym
The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
​Examples

{ { 'E', ' ', ' ' },

  {  ' ', 'E',  ' ' },

  {  ' ',  ' ', 'E' } }

we should put the chair at (1, 1), so that the sum of cost from the chair to the two equipments is 2 + 0 + 2 = 4, which is minimal.

    O(n ^ 2)
    O(n)
* */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceToPutTheChairII {
    public List<Integer> putChair(char[][] gym) {
        List<Point> equ = new ArrayList<>();
        Integer[] res = new Integer[2];

        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] == 'E') {
                    equ.add(new Point(i, j));
                }
            }
        }

        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < equ.size(); i++) {
            Point p1 = equ.get(i);
            int dis = 0;
            for (int j = 0; j < equ.size(); j++) {
                if (i != j) {
                    Point p2 = equ.get(j);
                    dis += Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
                }
            }
            if (dis < globalMin) {
                res[0] = p1.x;
                res[1] = p1.y;
                globalMin = dis;
            }
        }
        return Arrays.asList(res);
    }

    class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
