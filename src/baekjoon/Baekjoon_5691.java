package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 평균 중앙값 문제
public class Baekjoon_5691 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			if(a==0 && b==0) break;
			sb.append(2*a-b).append("\n");
		}
		System.out.println(sb.toString());
	}
}