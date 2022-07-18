package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 성택이의 은밀한 비밀번호
public class Baekjoon_25372 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			int n = br.readLine().length();
			if(n<6 || n>9) sb.append("no").append("\n");
			else sb.append("yes").append("\n");
		}
		System.out.println(sb.toString());
	}
}