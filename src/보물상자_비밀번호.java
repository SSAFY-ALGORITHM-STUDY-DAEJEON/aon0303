import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class 보물상자_비밀번호 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
            int k = sc.nextInt();
			int[] nums = new int[28];

            
            //들어온 문자 분리해서 deque에 넣기 
            String st = sc.next();
            Deque<String> deque = new ArrayDeque<String>();
			
			for (int j = 0; j < n; j++) {
				deque.add(String.valueOf(st.charAt(j))); //뒤의 parseInt를 사용하기 위해
			}


			//비번돌리기
			for (int j = 0; j < n/4; j++) {//사이클 도는 횟수
                for(int r = 0; r <4 ; r++){
                    int tmp =0;
                    for (int j2 = 0; j2 < n/4; j2++) {//한면에 저장되는 단일 숫자의 갯수
                        tmp *= 16;
                        tmp += Integer.parseInt(deque.peekFirst(),16);
                        deque.addLast(deque.removeFirst());
                    }
                    nums[j*4 + r] = tmp;
            }
                deque.addLast(deque.removeFirst());
			}

            //중복 숫자 없애기 
            Set<Integer> set = new LinkedHashSet<>();
            for(int z: nums) {
                if(z != 0) //0인 초기화배열 없애기
                set.add(z);
            }

            List<Integer> list = new ArrayList<>();
            for(int z: set) list.add(z);
            
            Collections.sort(list, Collections.reverseOrder());
            //treesetd으로 구현하고 뒤에서 부터 출력하기 
             
            System.out.println("#" + (i+1) + " " + list.get(k-1));
		}
	}
}
