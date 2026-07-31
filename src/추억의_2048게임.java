import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class 추억의_2048게임 {

    static class Node{
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y =y;
        }
    }


    static public Deque<Node> swipe1(Deque<Node> st, int[][] board, int i,int j){
        if(board[i][j] == 0) return st;
        if(!st.isEmpty()
            && st.peekLast().x == board[i][j] && st.peekLast().y ==0 ) {
                            st.removeLast();
                            st.addLast(new Node(board[i][j]*2, 1));
        }else  st.addLast(new Node(board[i][j],0));

        return st;
    }
    static public void swipe2(Deque<Node> st, int[][] board, int i,int j){
        if(!st.isEmpty())
                                board[i][j] = st.removeFirst().x;
                            else
                                board[i][j] = 0;
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = sc.nextInt();
            String s = sc.next();
            int[][] board = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int j2 = 0; j2 < n; j2++) {
                    board[j][j2] = sc.nextInt();
                }
            }


        Deque<Node> st = new LinkedList<>();
            switch(s){
                case "up" :
                case "left":
                    for (int j = 0; j < n; j++) {
                        for (int j2 = 0; j2 < n; j2++) {
                            if(s.equals("up")) st = swipe1(st, board, j2, j);
                            if(s.equals("left")) st = swipe1(st, board, j, j2);
                        }

                        for (int j2 = 0; j2<n; j2++) {
                            if(s.equals("up"))  swipe2(st, board, j2, j);
                            if(s.equals("left"))  swipe2(st, board, j, j2);
                        }
                    } break;
                case "down":
                case "right":
                    for (int j = 0; j <n; j++) {
                        for (int j2 = n-1; j2 >=0; j2--) {
                            if(s.equals("down")) st = swipe1(st, board, j2, j);
                            if(s.equals("right")) st =  swipe1(st, board, j, j2);
                        }

                        for (int j2 = n-1; j2 >=0; j2--) {
                            if(s.equals("down")) swipe2(st, board, j2, j);
                            if(s.equals("right"))  swipe2(st, board, j, j2);
                        }
                    } break;
            }

            System.out.println("#" + i);
            for (int j = 0; j < n; j++) {
                for (int j2 = 0; j2 < n; j2++) {
                    System.out.print(board[j][j2]+ (j2 == n-1 ? "\n":" "));
                }
            }

        }

    }

}
