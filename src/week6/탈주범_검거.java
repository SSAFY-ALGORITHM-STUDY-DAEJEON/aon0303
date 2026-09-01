import java.io.*;
import java.util.*;

public class 탈주범_검거 {
	//0: 상, 1: 하, 2: 좌 3: 우 
	static int[] dc = {0,0,-1,1};
	static int[] dr = {-1,1, 0,0};
	static int availPlace;
	static int[] opposite = {1, 0, 3, 2};

	static int [][] dn = {
			{},
			{0,1,2,3},
			{0,1},
			{2,3},
			{0,3},
			{1,3},
			{1,2},
			{0,2},
	};
	private static boolean isConnected(int direction, int nextNum) {
			
			for (int i : dn[nextNum]) {
				if(i == opposite[direction]) return true;
				
			}
			return false;
		}
	


	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 * 목표 
		 * 탈주범이 위치할 수 있는 장소의 개수 
		 * 
		 * 풀이 
		 * 1. bfs를 이용해 맨홀이 있는 곳에 부터 탐색을 한다 (cnt =1)
		 * 2. 탐색한위치를 -1로 변경한다
		 * 3. 탐색 중 발견한 숫자에 해당하는 위치를 탐색한다 (cnt +1) 
		 * 4. 발견 숫자가 현재 숫자와 이어지는지 확인한다.
		 * 
		 * 
		 * 주의 
		 * 번호에 해당하는 idx를 최적화 
		 * 경계값 주의
		 * 해당값이 0일경우 건너뛰기 
		 * L >= cnt 이어야 한다. 
		 * 
		 * 
		 * 알게된 점 
		 * 탐색 위치에 숫자를 발견해도 그게 이어지지 않은 경우 일 수 있다. 
		 * 반대방향을 만들어서 체크한다. 
		 *isConnected 
		 *num의 idx에 해당하는opposite[idx] 가 nextNum에 존재하면된다? 
		 */
		
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		StringTokenizer st ;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			Queue<int[]> q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			availPlace =0;
			
			
			int board[][] = new int[N][M];
			for (int n = 0; n < board.length; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < board[0].length; m++) {
					board[n][m] = Integer.parseInt(st.nextToken());
				}
				
			}
			
		
			q.add(new int[] {R, C, 0});
			
			
			while(!q.isEmpty()) {
				
//				System.out.println("===== 현재 board =====");
//
//			    for (int i = 0; i < N; i++) {
//			        for (int j = 0; j < M; j++) {
//			            System.out.print(board[i][j] + " ");
//			        }
//			        System.out.println();
//			    }
//
//			    System.out.println("====================");
				
				
				int cur[] = q.poll();
				
				int r = cur[0];
				int c = cur[1];
				int cnt = cur[2] +1;
	
				
				int num = board[r][c];
				board[r][c] = -1;
				
				if( L < cnt || num ==0 || num ==-1 ) {
					continue;
				}
				
				availPlace += 1;
				
				if(num != 0 && num != -1) {
					for (int i = 0; i < dn[num].length; i++) {
						int direction = dn[num][i];
						
						int nx = r + dr[direction];
						int ny = c + dc[direction];
						
						if( nx <0 || ny <0 || nx >= N || ny >= M ) continue;
						if(board[nx][ny] == 0 || board[nx][ny] ==-1)continue;
						int nextNum = board[nx][ny];
						if(!isConnected(direction, nextNum)) continue;
						
						
						q.add(new int[] {nx, ny, cnt});
						
						
						
					}
				}
		}
			System.out.println("#" + t + " " + availPlace);
		
		

	}

}



	
}
