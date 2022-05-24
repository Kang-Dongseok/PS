package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 별 찍기 - 8
public class Baekjoon_2445 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=i; ++j) {
				sb.append("*");
			}
			for(int j=2*(N-i); j>0; --j) {
				sb.append(" ");
			}
			for(int j=1; j<=i; ++j) {
				sb.append("*");
			}
			sb.append("\n");
		}
		for(int i=N-1; i>0; --i) {
			for(int j=1; j<=i; ++j) {
				sb.append("*");
			}
			for(int j=2*(N-i); j>0; --j) {
				sb.append(" ");
			}
			for(int j=1; j<=i; ++j) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}