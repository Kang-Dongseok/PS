package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 하노이탑 이동순서
public class Baekjoon_11729 {
//시간초과
//출력이 많을 때 StrinBuilder 반드시 이용할 것!

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = (1<<n) - 1;
		System.out.println(count);
		hanoi(n, 1, 3);
		System.out.println(sb.toString());
		
	}

	public static void hanoi(int n,int start,int dest) {
		if(n==1) {
			sb.append(start+" "+dest+"\n");
			return;
		}
		hanoi(n-1, start, 6-start-dest);
		sb.append(start+" "+dest+"\n");
		hanoi(n-1,6-start-dest,dest);
	}
}
