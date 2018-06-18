package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Primes {
    public List<Integer> primes(int target) {
        List<Integer> sol = new ArrayList<>();
        boolean[] notPrime = new boolean[target + 1];
        for (int i = 2; i <= target; i++) {
            if (notPrime[i]) continue;
            if (isPrime(i)) {
                sol.add(i);
                for (int j = 2; i * j <= target; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return sol;
    }
    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
