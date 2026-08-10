package swea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 나무_높이 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(new File("src/swea/input.txt"));
		
		int t = sc.nextInt();
		for (int tc= 0; tc < t; tc++) {
			int dayCount = 0;
			int treeNum = sc.nextInt();
			
			int maxH =0;
			int[] arr = new int[treeNum];
			
			
			for (int i = 0; i < treeNum; i++) {
				arr[i] = sc.nextInt();
				 maxH = Math.max(maxH, arr[i]);
			}
			
			
			int cntTwo =0, cntOne =0;
			//각 나무의 커야할 키를 구한다 
			for (int i = 0; i < treeNum; i++) {
				int diff = maxH - arr[i];
				cntOne += diff%2; //grow +1이 필요한 횟수, 홀수날이 필요한 경우
				cntTwo += diff/2; //grow +2이 필요한 횟수, 짝수날이 필요한 경우
					
			}
			while(cntTwo > cntOne + 1) {
				cntOne += 2;
				cntTwo --;
			}
			

			//첫째날로 끝나는 경우
			if(cntOne > cntTwo) dayCount = cntOne * 2 -1;
			else dayCount = cntTwo *2;
			System.out.println("#" + (tc+1) + " " + dayCount);
			
			//출력
		}
		
	}

}
