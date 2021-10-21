package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사파리월드
public class Baekjoon_2420 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		long N = Integer.parseInt(line[0]);
		long M = Integer.parseInt(line[1]);
		System.out.println(Math.abs(N-M));
	}
}