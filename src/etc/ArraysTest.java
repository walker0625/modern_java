package etc;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArraysTest {

    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];

        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println(System.nanoTime() - start);

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start2 = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println(System.nanoTime() - start2);
    }

}
