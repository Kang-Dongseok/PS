package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 연세대학교
public class Baekjoon_15680 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N==0) {
			System.out.println("YONSEI");
		}else {
			System.out.println("Leading the Way to the Future");
		}
	}
}