package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 너의 이름은 몇 점이니?
public class Baekjoon_15813 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int sum = 0;
		for(int i=0; i<N; ++i) {
			sum += str.charAt(i)-64;
		}
		System.out.println(sum);
	}
}