package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 세탁소 사장 동혁
public class Baekjoon_2720 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; ++t) {
			int n = Integer.parseInt(br.readLine());
			int quarter = n/25;
			n %= 25;
			int dime = n/10;
			n %= 10;
			int nickel = n/5;
			n %= 5;
			int penny = n;
			sb.append(quarter).append(" ").append(dime).append(" ").append(nickel).append(" ").append(penny).append("\n");
		}
		System.out.println(sb.toString());
	}
}