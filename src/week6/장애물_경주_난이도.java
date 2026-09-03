import java.io.*;
import java.util.*;

public class 장애물_경주_난이도 {
	
	static int T, N;
	static int[] heights;

	public static void main(String[] args) throws Exception{
		/*
		 * 목표: 가장 심한높이 변화 구하기 (내림, 올림)
		 * 
		 * 풀이 
		 * pre< cur인 경우 maxAsc update
		 * cur < pre 인 경우 maxDes update
		 */

		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st ;
		
		T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			int maxAsc =0, maxDes =0;
			N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			heights = new int[N];
			
			int pre, cur;
			for (int n = 0; n < N; n++) {
				heights[n] = Integer.parseInt(st.nextToken());
				
				if(n !=0) {
					pre = heights[n-1];
					cur = heights[n];
					if(pre < cur) maxAsc = Math.max(maxAsc, cur -pre);
					else if (pre > cur ) maxDes = Math.max(maxDes,pre-cur);
				}
			}
			
			
			System.out.println("#" + t + " " + maxAsc + " " + maxDes);
		}
		
	}

}
