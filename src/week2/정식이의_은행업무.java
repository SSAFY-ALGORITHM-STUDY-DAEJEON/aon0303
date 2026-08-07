import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class 정식이의_은행업무 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("input.txt"));
		
		int T = sc.nextInt();
		char[] twoCount = {'0', '1'};
		char[] threeCount = {'0','1','2'};
		for (int tc = 0; tc < T; tc++) {
			char[] two = sc.next().toCharArray();
			char[] three = sc.next().toCharArray();
			
			//hashset은 중복된 원소를 add할 경우 false를 반환한다 
			HashSet<Long> hm = new HashSet<>();
			
			
			//이진수 한개씩 바꿔서 해쉬맵에 저장하기 
			for (int i = 0; i < two.length; i++) {
				if(two[i] =='0') {
					char[] tmp = two.clone();
					tmp[i] = '1';
					hm.add(Long.parseLong(new String(tmp), 2));
				}else {
					char[] tmp = two.clone();
					tmp[i] = '0';
					hm.add(Long.parseLong(new String(tmp), 2));
				}
			}
			//삼진수 한개씩 넣을 때 if문으로 false인 경우 sysout
			//이거 어떻게 최적화 할것?
			boolean endflag = false;
			for (int i = 0; i < three.length; i++) {
				for (int j = 0; j < threeCount.length; j++) {
					if(endflag) break;
					if(threeCount[j] == three[i]) continue;
					char[] tmp = three.clone();
					tmp[i] = threeCount[j];
					
					if(!hm.add(Long.parseLong(new String(tmp), 3))) {
						Long answer = Long.parseLong(new String(tmp), 3);
						System.out.println("#" + (tc+1) + " " + answer);
						endflag = true;
					}
				}
			}
			
		}

	}

}
