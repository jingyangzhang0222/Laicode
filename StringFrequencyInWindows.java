package leetcode;

import java.util.*;

public class StringFrequencyInWindows {
    public static void main(String[] args) {
        StringFrequencyInWindows test = new StringFrequencyInWindows();
        List<Frequency> sol = test.frequency("ABCDABCDD");
        for (Frequency f : sol) {
            System.out.print(f.str);
            System.out.print(' ');
            System.out.print(f.freq);
            System.out.println();
        }
    }

    public List<Frequency> frequency(String input) {
        List<Frequency> sol = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int[] freMap = new int[4];
        int type = 0;

        for (int f = 0; f < input.length(); f++) {
            if (freMap[input.charAt(f) - 'A']++ == 0) type++;
            if (type == 4) {
                String substr = input.substring(f - 4 + 1, f + 1);
                if (!map.containsKey(substr)) {
                    map.put(substr, 0);
                }
                int frequency = map.get(substr);
                map.put(substr, frequency + 1);
            }
            if (f >= 3 && --freMap[input.charAt(f - 4 + 1) - 'A'] == 0) type--;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sol.add(new Frequency(entry.getKey(), entry.getValue()));
        }
        Collections.sort(sol, new MyComparator());
        return sol;
    }

    static class MyComparator implements Comparator<Frequency>{
        @Override
        public int compare(Frequency f1, Frequency f2) {
            int f1Value = -10000 * f1.freq + 1000 * (f1.str.charAt(0) - 'A') + 100 * (f1.str.charAt(1) - 'A')
                    + 10 * (f1.str.charAt(2) - 'A') + f1.str.charAt(3) - 'A';
            int f2Value = -10000 * f2.freq + 1000 * (f2.str.charAt(0) - 'A') + 100 * (f2.str.charAt(1) - 'A')
                    + 10 * (f2.str.charAt(2) - 'A') + f2.str.charAt(3) - 'A';

            return f1Value - f2Value;
        }
    }

    public class Frequency {
        public String str;
        public int freq;

        public Frequency(String str, int freq) {
            this.str = str;
            this.freq = freq;
        }
    }
}
