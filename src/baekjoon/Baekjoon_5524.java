package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 입실 관리
public class Baekjoon_5524 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			sb.append(br.readLine().toLowerCase()).append("\n");
		}
		System.out.println(sb.toString());
	}
}