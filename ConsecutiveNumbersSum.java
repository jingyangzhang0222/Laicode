package leetcode;

public class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int quo = N / i;
            if (quo < (i + 1) / 2) {
                break;
            }
            if ((i % 2 == 1 && quo * i == N) || (i % 2 == 0 && (quo + 1) * i - N == N - quo * i)) {
                count++;
            }
        }
        return count;
    }
}
