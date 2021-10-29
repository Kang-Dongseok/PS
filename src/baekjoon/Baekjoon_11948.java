package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 과목선택
public class Baekjoon_11948 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int F = Integer.parseInt(br.readLine());
		int min1 = Math.min(Math.min(A, B), Math.min(C, D));
		int min2 = Math.min(E, F);
		System.out.println(A+B+C+D+E+F-min1-min2);
	}
}
