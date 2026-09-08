import java.util.*;
import java.io.*;

public class 사과먹기 {
	
	static int T, N, M, answer, mx,cirCntmn;
	static int[] curDir = {0, 1, 2, 3};
	//상, 하, 좌, 우, 
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] board = new int[10][10];
	static int[][][][] dist;
	//우, 좌, 상, 하 
	static int[] availDir = {3, 2, 0, 1};
	
	

	public static void main(String[] args) throws Exception{
		/*
		 * 목표
		 * 최소회전횟수로 순차가 있는 사과 먹기 
		 * 
		 * 풀이 (0-1 bfs)
		 * 1. 비내림차순 ( 커지거나 같게) deque에 node를 넣는다 
		 * 2. dist를 통해서 해당 상태에 대한 dist가 가장 작은 애로 업글시켜주면서 
		 * 3. target == mx값이 되면 그때의 cirCnt값이 가장 최소의 회전 수 
		 * 
		 * 주의 
		 *1. 사과는 바로 직전 사과와 동일한 열/행에 나타나지 않는다 
		 *2. 지도의 가장자리 영역에 사과가 나타나지 않는다
		 *3. 오직 오른쪽으로만 회전이가능하다
		 *4. 한 자리에서 오직 한번만 회전이 가능하다  (사과를 먹음과 동시에 가능) 
		 * => 그냥 뒤로 가는걸 못한다고 생각해야되나  
		 * => 본인이 방향을 가지고있다???
		 */ 

		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			dist = new int[N][N][4][10];// r, c, dir, target <- 과거에 뭘 했는지가 미래를 바꾸면 상태 
			
			for (int r = 0; r < N; r++) {
			    for (int c = 0; c < N; c++) {
			        for (int d = 0; d < 4; d++) {
			            Arrays.fill(dist[r][c][d], Integer.MAX_VALUE);
			        }
			    }
			}

			
			
			 mx =0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					mx = Math.max(mx, board[i][j]);
				}
			}
			
			
		Deque<int[]> q = new ArrayDeque<int[]>();
		dist[0][0][3][1] = 0;
		q.offer(new int[]{0,0,3,1});// r, c, dir, 회전횟수, target
		
		answer = Integer.MAX_VALUE;
		int r, c, dir, adir, cirCnt, target;
		while(!q.isEmpty()) {

			int[] cur = q.poll();
			
			r = cur[0];
			c = cur[1];
			dir = cur[2];
			adir = availDir[dir];
			target = cur[3];
			
			cirCnt = dist[r][c][dir][target];

			
			if(board[r][c] == target) {
				if(target == mx) { //비내림차순으로 정렬되어있기 때문에 가능하다 
					answer = cirCnt;
					break;
				}
				target++;
				
			}
			
			//같은 dir, 회전 dir 두개를 가질 수 있음
			//같은 dir로 갈 때는 그냥 쭉 감
			//회전 dir로 갈 때는 cirCnt 를 증가 시킨다. 
			
			//현재 디렉션은 dir와 그대로 curDir,회전 availDir
		
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr >=0 && nr < N && nc >=0 && nc< N) {
				if(dist[nr][nc][dir][target] > cirCnt){ //현재 상태의 dist가 적힌 dist 보다 작은경우 
					dist[nr][nc][dir][target] = cirCnt;
					
					q.offerFirst(new int[] {nr, nc, dir, target}); // 0-1 dfs 
					
				}
			}
			
			
			int ar = r + dr[adir];
			int ac = c + dc[adir];
			if(ar >=0 && ar < N && ac >=0 && ac< N) {
				if(dist[ar][ac][adir][target] > cirCnt +1) {
					dist[ar][ac][adir][target] = cirCnt+1;
					q.offerLast(new int[] {ar, ac, adir, target});
					
				}
			}
			
			
		}
		
		
	
	
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}
