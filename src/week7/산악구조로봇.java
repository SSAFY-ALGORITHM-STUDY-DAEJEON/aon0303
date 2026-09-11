import java.util.*;
import java.io.*;

public class 산악구조로봇 {
	
	static int T, N;
	static int[][] map;
	static int[][] dist;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int cost;
		
		public Node(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(산악구조로봇.Node o) {
			
			return cost - o.cost;
		}
		
		
		
	}


	public static void main(String[] args) throws Exception{
		
		
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	
        	map = new int[N][N];
        	dist = new int[N][N];
        	
        	for (int i = 0; i <N; i++) {
        		st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
        	
        	int answer = djikstra();
        	
        	System.out.println("#" + t + " " + answer );
        	
        	
        }

	}


	private static int djikstra() {
		
		PriorityQueue<Node> p = new PriorityQueue<>();
		
		//dist 값이 업뎃 되기 위해서는 처음에 dist 값을 ingteger.max값으로 초기화 
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		p.offer(new Node(0,0,0));
		dist [0][0] =0;
		
		
		while(!p.isEmpty()) {
			Node cur = p.poll();
			
			int r = cur.r;
			int c = cur.c;
			int cost = cur.cost;
			
			if(r == N-1 && c == N-1) {
				return cost;
			}
			
			if(dist[r][c] < cost) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr <0 || nr >=N || nc <0 || nc >= N) continue;
				
				int nowH = map[r][c];
				int nextH = map[nr][nc];
				int moveCost;
				
				if(nowH > nextH) 
					moveCost = 0;
				else if (nowH == nextH ) 
					moveCost = 1;
				else moveCost = (nextH - nowH)*2;
				
				
				int nextCost = moveCost + cost;
				
				if(dist[nr][nc] <= nextCost) continue;
				
				dist[nr][nc] = nextCost;
				p.offer(new Node(nr,nc,nextCost));
				
			}
		}
		
		
		return 0;
	}

}
