package swea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack; 


public class 쇠막대기_자르기 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("src/swea/input.txt"));
		
				
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			Stack<Character> s = new Stack<>();
			String input = br.readLine();
			int cnt =0;
		
			for (int i = 0; i < input.length(); i++) {
				if(s.isEmpty()) {
					s.add(input.charAt(i));
					continue;
				}
				if(s.peek() == '(' && input.charAt(i)== ')') {
					s.pop();
					//지금이 레이저가 아닌 경우는 그냥 닫는 경우여서 조각이 +1
					if(input.charAt(i-1) != '(') {
						cnt ++;
					}else cnt += s.size();
						
					
				}else s.add(input.charAt(i));
			}
			System.out.println("#"+ t + " " + cnt);
		}
	}

}
