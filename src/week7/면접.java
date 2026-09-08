import java.io.*;
import java.util.*;


public class 면접 {
	/*
	 * 목표 : 철수 M개 문제를 맞추는 경우의 수 중 총점이 최소인 경우 
	 * 
	 * 풀이 
	 * 1.N개의 길이를 가진  true 초기화 인덱스를 가진다 
	 * 2.i = m-n 개 를 false 시킬 수 있는데 뒤에서 k 번째 인덱스를 false 으로 채운다 
	 * 3. 인덱스 길이를 넘어가면 마지막에 score에서 빼주고,인덱스 길이보다 적은 것도 주의해야한다 
	 *
	 *주의 
	* 계산식에서 값을 더해주는 부분 식을 잘못세움 
	 * 
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int I = N-M; //틀릴  수 있는 갯수 
			int score = 0;

			
			boolean[] flags = new boolean[N];
			Arrays.fill(flags, true);
			
			//뒤에서 부터 false 집어넣기 
			int cnt =1;
			for (int j = N-1; j >= 0; j--) {
				if(I<=0) break;
				if(cnt%K==0) {
					flags[j] = false;
					I--;
				}
				cnt++;
			}
			

			//실제 계산하는 구간 
			cnt =1;
			for (int j = 0; j < N; j++) {
				if(!flags[j]) {
					cnt =1;
					continue; 
				}
				score ++;
				if(cnt % K==0) {
					score *= 2;
				}
				cnt++;
			
			}
			//뺄수 있는 숫자가 더 남았으면 빼주기 
				if(I > 0) score -= I;
			
			
			sb.append("#")
			.append(t).append(" ").append(score).append("\n");
	
			
		
		}
		System.out.println(sb);

	}

}
