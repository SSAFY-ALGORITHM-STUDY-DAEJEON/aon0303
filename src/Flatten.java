import java.io.*;
import java.util.*;

public class Flatten {

	public static void main(String[] args) throws FileNotFoundException {;
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		for(int i =1; i<=10; i++) {
			int dump_num = sc.nextInt();
			int[] boxs = new int[100];

			for(int j =0; j< 100; j++) {
				boxs[j] = sc.nextInt();
			}
			for(int j =0; j< dump_num; j++) {
				int max_h = 0;
				int min_h = 101;
				int max_i=0 , min_i =0;
				for(int k = 0; k <100; k++) {
					if(max_h <= boxs[k]) {
						max_h = boxs[k];
						max_i = k;
					}
					if(min_h >= boxs[k]) {
						min_h = boxs[k];
						min_i = k;
					}

				}

				boxs[max_i] -= 1;
				boxs[min_i] += 1;

			}
			int max_h = Math.max(0 ,Arrays.stream(boxs).max().getAsInt());
			int min_h = Math.min(101 ,Arrays.stream(boxs).min().getAsInt());
			int result =  max_h- min_h;

			System.out.println("#" + i + " " + result);

		}
	}

}
