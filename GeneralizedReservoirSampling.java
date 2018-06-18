/*
* Consider an unlimited flow of data elements. How do you sample k element from this flow, such that at any point during the processing of the flow, you can return a random set of k elements from the n elements read so far.

Assumptions

k >= 1
You will implement two methods for a sampling class:

read(int value) - read one number from the flow
sample() - return at any time the k samples as a list, return the list of all values read when the number of values read so fas <= k.
You may need to add more fields for the class.
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedReservoirSampling {
    public static void main(String[] args) {
        GeneralizedReservoirSampling test = new GeneralizedReservoirSampling(3);
        test.read(1);
        test.read(2);
        test.read(3);
        test.read(4);
        test.read(5);
        test.read(6);
        test.read(7);
        for (Integer num : test.sample()) {
            System.out.println(num);
        }

    }
    private final int capacity;
    private List<Integer> reservior;
    private int count;
    //[0, ..., capacity - 1]

    public GeneralizedReservoirSampling(int k) {
        capacity = k;
        reservior = new ArrayList<>();
    }

    public void read(int value) {
        if (count < capacity) {
            count++;
            reservior.add(value);
        } else {
            count++;
            int replacedIndex = generateIntLessThanCount();
            if (replacedIndex < capacity) {
                reservior.set(replacedIndex, value);
            }
        }
    }

    public List<Integer> sample() {
        return reservior;
    }

    private int generateIntLessThanCount() {//[0, 1, ..., x - 1]
        return (int)(Math.random() * count);
    }
}
