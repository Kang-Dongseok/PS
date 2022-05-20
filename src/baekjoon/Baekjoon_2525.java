package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 오븐 시계
public class Baekjoon_2525 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int A = Integer.parseInt(str[0]);
		int B = Integer.parseInt(str[1]);
		int C = Integer.parseInt(br.readLine());
		B += C;
		int add = B/60;
		B %= 60;
		A += add;
		A %= 24;
		System.out.println(A+" "+B);
	}
}