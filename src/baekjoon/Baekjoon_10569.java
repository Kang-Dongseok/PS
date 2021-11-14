package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 다면체
public class Baekjoon_10569 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; ++tc) {
			String[] str = br.readLine().split(" ");
			int v = Integer.parseInt(str[0]);
			int e = Integer.parseInt(str[1]);
			sb.append(2+e-v).append("\n");
		}
		System.out.println(sb.toString());
	}
}