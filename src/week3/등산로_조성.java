import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 등산로_조성 {

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N, K;
	static int mxLen;
	static int[][] board = new int[8][8];
	static boolean[][] v = new boolean[8][8];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());

		//시작점 : 처음에 집어넣으면서 mx를 찾는다 
		
		
		//해당 블럭을 방문했지, 규격을 벗어났는지는 기본 
		//1. dfs 해당 블럭이 본인 보다 낮은지 확인,
		//2. 주변에 본인보다 낮은 블럭이 없을 때, 내가 이미 플래그를 썼는지 안썼는지 확인 
		//3. 최대 깊이 k를깎으면 본인보다 작아지는지 확인, 나의 크기 보다 -1이 되도록크기를 설정 
		//4. maxH값 계속 업데이트 후 마지막에 출
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			mxLen =0;
			int mx =0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					mx = Math.max(mx, board[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(board[i][j] == mx) {
						v[i][j] = true;
						dfs(i, j, 1, false);
						v[i][j] = false;
					}
				}
			}
		System.out.println("#" + t + " " + mxLen);
			
		}

	}

	private static void dfs(int i, int j, int len, boolean flag) {
		 
		mxLen = Math.max(mxLen, len);
		
		for (int k = 0; k < 4; k++) {
			int nx = i + dr[k];
			int ny = j + dc[k];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(v[nx][ny]) continue;
			
			//갈 수 있는 경우 
			if(board[nx][ny] < board[i][j]) {
				v[nx][ny] = true;
				dfs(nx, ny, len+1, flag);
				v[nx][ny] = false;
			}
			//깎아서 가는 경우
			else if (!flag && board[nx][ny]-K < board[i][j]) {

			    int original = board[nx][ny];

			    board[nx][ny] = board[i][j] - 1;

			    v[nx][ny] = true;
			    dfs(nx, ny, len + 1, true);
			    v[nx][ny] = false;
			    
			    board[nx][ny] = original;
			}
			
		}
		
	
		
	}

}
