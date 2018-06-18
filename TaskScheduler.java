/*
* Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different
* letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval.
* For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals
that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].


* */
package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskScheduler {
    public static void main(String[] args) {
        TaskScheduler test = new TaskScheduler();
        String[] input = {"A", "A", "A", "A", "A", "A", "B", "C", "D", "E", "F", "G"};
        System.out.println(test.leastInterval(input, 2));
    }

    public int leastIntervalMath(String[] tasks, int n) {
        Task[] map = new Task[26];
        int maxTaskCount = 0;
        int maxFre = Integer.MIN_VALUE;
        for (String task : tasks) {
            if (map[task.charAt(0) - 'A'] == null) {
                map[task.charAt(0) - 'A'] = new Task(task.charAt(0));
            }
            map[task.charAt(0) - 'A'].fre++;

            if (map[task.charAt(0) - 'A'].fre > maxFre) {
                maxFre = map[task.charAt(0) - 'A'].fre;
                maxTaskCount = 1;
            } else if (map[task.charAt(0) - 'A'].fre == maxFre) {
                maxTaskCount++;
            }
        }

        return Math.max(tasks.length, (n + 1) * (maxFre - 1) + maxTaskCount);
    }

    public int leastInterval(String[] tasks, int n) {
        Task[] map = new Task[26];
        for (String task : tasks) {
            if (map[task.charAt(0) - 'A'] == null) {
                map[task.charAt(0) - 'A'] = new Task(task.charAt(0));
            }
            map[task.charAt(0) - 'A'].fre++;
        }
        PriorityQueue<Task> pq = new PriorityQueue<>(11, new MyComparator());
        for (int i = 0; i < 26; i++) {
            if (map[i] != null) {
                pq.offer(map[i]);
            }
        }
        int time = 0;
        while (!pq.isEmpty()) {
            time++;
            System.out.print(time + " ");
            if (pq.peek().lastTime != -1 && time - pq.peek().lastTime <= n) {
                System.out.println("IDLE");
                continue;
            }
            Task curTask = pq.poll();
            System.out.print(curTask.type + " ");
            System.out.println(curTask.fre + " ->" + (curTask.fre - 1));
            if (--curTask.fre > 0) {
                curTask.lastTime = time;
                pq.offer(curTask);
            }
        }
        return time;
    }

    static class Task {
        char type;
        int lastTime;
        int fre;

        public Task(char type) {
            this.type = type;
            lastTime = -1;
        }
    }

    static class MyComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            if (t1.lastTime != t2.lastTime) {
                return t1.lastTime < t2.lastTime ? -1 : 1;
            }
            if (t1.fre != t2.fre) {
                return t1.fre > t2.fre ? -1 : 1;
            }
            return 0;
        }
    }
}
