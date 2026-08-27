import java.io.*;
import java.util.*;



public class 숫자_게임 {
	static int maxTurn =0;
	static HashMap<Integer, Integer> hs = new HashMap<>();
	//메모리제이션을 쓰면서 최대 turn이 나오게 해야함 

	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 * 목표
		 * 가장 큰 turn 값 구하기
		 * 
		 * 풀이 
		 * 1. 시작수와 turn 수를 가지고 dfs 
		 * 2.bitmasking을 통해서 x가 들어갈 위치를 선택하고 자르면서  곱해준다 
		 * 3. 새로나온 시작수가 10 미만이 될 때 maxTurn수를 업데이트하고 return
		 * 4. maxTurn 출력  
		 * 
		 * 주의
		 * 곱한 수가 10미만 이면 더 쪼갤 수 없다 
		 */
		BufferedReader br = new BufferedReader(new FileReader ("input.txt"));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			hs.clear();
			int N = Integer.parseInt(br.readLine().trim());
			int turn =0;
			maxTurn =0;
			

			
			dfs(N, turn);
			
			System.out.println("#" + t + " " + maxTurn);
		}
		

	}

	private static void dfs(int n, int turn) {
		
		if(hs.containsKey(n) && hs.get(n) >=turn) return;
		hs.put(n, turn);
		
		String s = String.valueOf(n);
		
		if(n < 10) {
			maxTurn = Math.max(turn, maxTurn);
			return;
		}
		
		
		int size = s.length()-1;
		

		
		for (int mask = 1; mask < (1 << size); mask++) {
			int num =1;
			int pre = 0;
			
			for (int i = 0; i < size; i++) {
				
				if((mask & (1 << i))!= 0) {
					String subs = s.substring(pre,i+1);
					pre = i+1;
					
					num *= Integer.parseInt(subs);

					
				}
			}
			String subs = s.substring(pre,size+1);
			num *= Integer.parseInt(subs);
			

			dfs(num, turn+1);

			
			//System.out.println(n +" -> "+ num);
		}
		
		
	}

}
