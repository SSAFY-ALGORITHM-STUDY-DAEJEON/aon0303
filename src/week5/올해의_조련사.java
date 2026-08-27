import java.io.*;
import java.util.*;

public class 올해의_조련사 {
	
	public static int comparison(char[] board, int i, int j) {
	
		while(i <= j) {
			if(board[i] == board[j]) {
				i++; j--;
			}else if (board[i] > board[j]) return 1;
			else return 0;
		}
		return 0;
		
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 * 목표
		 * 경근이의 방식으로 사전순으로 가장 빠른 문자열 출력 
		 * 
		 * 풀이 
		 * 1. 배열을 가지고 앞과 맨뒤를 비교하면서 정렬한다 
		 * 2. i앞 인덱스가 j보다 같거나 클경우에 stop
	
		 * 
		 * 주의 
		 * 
		 * 같은 값을 가진 경우에 그 다음 값들을 비교한 후 , 그 다음 값들도 같다면 , 그 다음값들을 또 비교해야되나 ...
		 * 
		 *
		 */
		
		BufferedReader br = new BufferedReader (new FileReader("input.txt"));
		
		StringTokenizer st ;
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine().trim());
		
			char board[] = new char[N];
			
			for (int n = 0; n < N; n++) {
				board[n] = br.readLine().trim().charAt(0);
			}
			
			System.out.print("#" + t + " ");
			
			int i = 0;
			int j = N-1;
			while(i<= j) {
				if(board[i] < board[j]) {
					System.out.print(board[i]);
					i++;
				}else if(board[i] == board[j]) {
					int result = comparison(board, i, j);
					if(result == 0) {
						System.out.print(board[i]);
						i++;
					}else if(result == 1) {
						System.out.print(board[j]);
						j--;
					}
				}else {
					System.out.print(board[j]);
					j--;
				}
			}
			System.out.println();
		}
		

	}
	
	

}
