import java.io.*;
import java.util.*;

public class 활주로_건설 {
	
	static int board[][];
	static int N, X;

	public static void main(String[] args) throws NumberFormatException, IOException {
		/* 입력
		 * 
		 * T
		 * N X
		 * N * N matrix
		 * 
		 * 목표 
		 * 활주로를 건설할 수 있는 경우의 수(경사로 활용) 
		 * 
		 * 풀이 (한줄) 
		 * 1. 각 열을 분리하고, 스택에 (value, cnt, used)를 넣는다. 
		 * 2. stack을 이용해 같은 숫자가 들어오면 cnt값을 증가하고 이전 값은 삭제 한다 
		 * 3. used를 사용해 이미 사용됬는지 확인하여 설치 가능성을 체크한다 
		 * 
		 * 
		 * 
		 * 주의 
		 * 가로, 세로 둘 다 확인 
		 * 
		 * 생각 못한 부분 
		 * x= 4 [2,2][1,6][2,1]인 경우 불가능함..
		 */
		
		
		BufferedReader in = new BufferedReader(new FileReader ("input.txt"));
		StringTokenizer st;
		

		int T = Integer.parseInt(in.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			
			board = new int[N][N];
			int cnt =0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				if(checkAvail(board[i])) cnt++;//가로일  경우 설치 가능한지 확인 
			}
			//세로일 경우 설치확인 
			
			for (int c = 0; c < N; c++) {
				int[] a = new int[N];
				for (int i = 0; i < N; i++) {
					a[i] = board[i][c];
				}
				if(checkAvail(a)) cnt++;
				
			}
			
			
			System.out.println("#" + t + " " + cnt);
		}
	}

	//stack으로 구현 
	private static boolean checkAvail(int[] board) {
		
	
		boolean flag = true;
		
		Stack<int[]> st = new Stack<>();
		//넣을 때 차이의 절댓값이 1이 아니면 return false;
		for (int j = 0; j < board.length; j++) {
			if(!st.isEmpty()){
				if(st.peek()[0] == board[j]) {
					st.peek()[1] += 1;
				}
				else {
					if(Math.abs(st.peek()[0] - board[j])!= 1) return false;
					st.push(new int[]{board[j],1, 0});
				}
				
			}
			else st.push(new int[]{board[j],1, 0});
		}
		
//		for(int[] a : st) {
//			System.out.print(Arrays.toString(a));
//		}
		
		
		int cur[] = st.pop();

		
		while(!st.isEmpty()) {
			int next[] = st.pop();
			int small[] = (next[0] > cur[0]) ? cur : next;

			
			if(small[1] < X) {
				return false;
			}else if (small[2] ==1 ) {
				if(small[1] - X < X ) return false;
			//else if (small[1] < X * small[2]) return false;이런식으로도 가능
			}
			else {
				small[2] = 1;
				flag = true;
			}
				
			cur = next;
		}
					
		
		
			
		return flag;
		
	}
}

/*
 * private static boolean checkAvail(int[] line) {
    boolean[] used = new boolean[N];

    for (int i = 0; i < N - 1; i++) {

        int diff = line[i + 1] - line[i];

        if (diff == 0) continue;

        if (Math.abs(diff) > 1) {
            return false;
        }

        // 내려가는 경우
        if (diff == -1) {
            for (int j = i + 1; j <= i + X; j++) {
                if (j >= N) return false;
                if (line[j] != line[i + 1]) return false;
                if (used[j]) return false;

                used[j] = true;
            }
        }

        // 올라가는 경우
        else {
            for (int j = i; j > i - X; j--) {
                if (j < 0) return false;
                if (line[j] != line[i]) return false;
                if (used[j]) return false;

                used[j] = true;
            }
        }
    }

    return true;
}
 */


