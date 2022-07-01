package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// SASA 모형을 만들어보자
public class Baekjoon_23825 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0])/2;
		int M = Integer.parseInt(str[1])/2;
		System.out.println(Math.min(N, M));
	}
}