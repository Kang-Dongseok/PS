package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
// 4연산
public class Baekjoon_14395 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		final int MAX = 1000000000; // 10^9
		long s = Long.parseLong(str[0]);
		int t = Integer.parseInt(str[1]);
		if(s==t) {
			System.out.println(0);
			return;
		}
		Queue<String[]> q = new LinkedList<String[]>();
		q.offer(new String[] {String.valueOf(s*s),"*"});
		q.offer(new String[] {String.valueOf(s*2),"+"});
		q.offer(new String[] {"1","/"});
		
		String answer = "";
		while(!q.isEmpty()) {
			String[] cur = q.poll();
			long num = Long.parseLong(cur[0]); // 현재 숫자, 곱셈 오버플로우 방지를 위해 long타입
			String op = cur[1]; // 현재 연산자 문자열
			if(num==t) {
				answer=op;
				break;
			}
			if(num>1 && num*num<=t) {
				q.offer(new String[] {String.valueOf(num*num),op+"*"});
			}
			if(num!=2 && num*2<=t) {
				q.offer(new String[] {String.valueOf(num*2),op+"+"});
			}
		}
		if(answer.equals("")) { // 답이 없으면
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
	}
}