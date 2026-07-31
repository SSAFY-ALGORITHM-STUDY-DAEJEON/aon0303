package week2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 재관이의_대량할인 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("week2/input.txt"));
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int index = 0; index < t; index++) {
            int payment = 0;
            int n = sc.nextInt();
            Integer[] c = new Integer[n];
            int[] cart = new int[3];
            for (int i = 0; i < n; i++) {
                c[i] = sc.nextInt();
            }

            int total = Arrays.stream(c)
                    .mapToInt(Integer::intValue)
                    .sum();
            Arrays.sort(c, Comparator.reverseOrder());

            int iter = n / 3;

            for (int i = 0; i < iter * 3; i++) {
                cart[i % 3] = c[i];

                if (i % 3 == 2 && i != 0) {
                    payment += Arrays.stream(cart).min().getAsInt();
                }
            }

            System.out.println("#" + (index + 1) + " " + (total - payment));
        }

    }
}
