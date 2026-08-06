import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class 재미있는_오셀로_게임 {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("./input.txt"));
		int t = sc.nextInt();
		int[] dx = {0,0,1,1,1,-1,-1,-1};
		int[] dy = {-1,1,-1,0,1,-1,0,1};
		
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			int[][] board = new int[n][n];
			int m = sc.nextInt();
			
			board[n/2 -1][n/2-1] = 2;
			board[n/2][n/2] = 2;
			
			board[n / 2 - 1][n / 2] = 1;
            board[n / 2][n / 2 - 1] = 1;

			for (int j = 0; j < m; j++) {
				int x = sc.nextInt()-1;
				int y = sc.nextInt()-1;
				board[x][y] = sc.nextInt();
				
				//주변 돌 색 확인 
				for (int k = 0; k < 8; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if(nx <0 || nx >= n|| ny <0 || ny >= n) continue;
					if(board[nx][ny] == 0) continue;
					if(board[nx][ny] != board[x][y]) {

						int cx = nx ;
						int cy = ny ;
						//cx, cy가 경계값이 되기 전까지
						//현재 보드값과 같은 값이 나오면 stop 
						//사이 돌 색 뒤집기 
						
						int cnt =0;
						while(cx >=0 && cx < n && cy >=0 && cy < n &&
								board[cx][cy] == board[nx][ny]) {
							cx += dx[k];
							cy += dy[k];
							cnt++;
						}
						//코드안에서 경계값 검사를 할 경우 무조건 마지막에 while 문에서 경계가 넘어감 
						
						//경계값 검사 무조건 해야함, 어느 로 나왔는지 몰라서 다음 if문에 out
						if(cx <0 || cx >= n|| cy <0 || cy >= n) continue;
						//원하는 색을 찾은 경우, 돌 색 뒤집기 
						if(board[cx][cy] == board[x][y]) {
							for (int l = 0; l < cnt; l++) {
								cx -= dx[k];
								cy -= dy[k];
								board[cx][cy] = board[x][y];
							}
						}
						
					}
					
				}
				
			}
			int w_cnt= 0;
			int b_cnt= 0;
			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					if(board[j][j2] == 1) b_cnt++;
					else if(board[j][j2] ==2 ) w_cnt++;
				}
				
			}
			
			System.out.println("#"+(i+1)+" " + b_cnt + " " + w_cnt);
		}

	}

}
