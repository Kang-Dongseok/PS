package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 타일 채우기 4
public class Baekjoon_15700 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		long N = Long.parseLong(str[0]);
		long M = Long.parseLong(str[1]);
		System.out.println(N*M/2);
	}
}
