package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Do Not Touch Anything
public class Baekjoon_13136 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int R = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		int N = Integer.parseInt(str[2]);
		long a = R%N==0 ? R/N : R/N+1;
		long b = C%N==0 ? C/N : C/N+1;
		System.out.println(a*b);
	}
}