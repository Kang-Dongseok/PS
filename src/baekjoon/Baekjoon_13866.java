package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팀 나누기
public class Baekjoon_13866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int A = Integer.parseInt(str[0]);
		int B = Integer.parseInt(str[1]);
		int C = Integer.parseInt(str[2]);
		int D = Integer.parseInt(str[3]);
		int answer = Math.min(Math.abs(A+C-B-D), Math.abs(A+D-B-C));
		System.out.println(answer);
	}
}
