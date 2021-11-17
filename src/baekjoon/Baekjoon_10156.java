package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 과자
public class Baekjoon_10156 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int K = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		int M = Integer.parseInt(str[2]);
		System.out.println(Math.max(K*N-M,0));
	}
}
