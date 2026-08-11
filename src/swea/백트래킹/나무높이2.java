import java.io.*;
import java.util.*;


public class 나무높이2 {
	static int mn = 0;

	//백트래킹 연습용
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			int N = Integer.parseInt(br.readLine());
			int[] pots = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int mx = 0;
			for (int n = 0; n < N; n++) {
				pots[n] = Integer.parseInt(st.nextToken());
				mx = Math.max(mx, pots[n]);
			}
			
			int[] needs = new int[N];
			for (int n = 0; n < N; n++) {
				needs[n] = mx - pots[n];
			}
			
			mn = Arrays.stream(needs).sum() ; //가지치기를 하려면 적당한 수여야 함,아니면 최악의경우에서 이 경우까지 day가 cnt
			dfs(needs, 1);
			System.out.println("#"+ t + " " + mn);
		}
		
		
		
	}
/**
 * 
 * @param needs : 필요한 높이 
 *
 * @param dayCount : 날짜세기 
 */
	
	private static void dfs(int[] needs, int dayCount) {
		// TODO Auto-generated method stub
		
		if(Arrays.stream(needs).sum() == 0) {
			
			mn = Math.min(mn,  dayCount- 1); //증가되어 넘어오기 때문에 
			
			return;
		}
		if(dayCount > mn) return;
		//inductive
		int grow = (dayCount-1) %3 +1;
		
		for (int j = 0; j < needs.length; j++) {
			//지금 화분에 넣는 경우
			if(needs[j] >= grow) {
				needs[j] -= grow;
				dfs(needs,dayCount+1);
			
				//백트래킹 
				needs[j]+= grow;
				
			}
		}
		//아무곳에도 안넣는 경우 
		dfs(needs,dayCount+1);
		
	}
	

}
