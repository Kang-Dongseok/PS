package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 뜨거운 붕어빵
public class Baekjoon_11945 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			StringBuilder sb2 = new StringBuilder();
			sb2.append(br.readLine()).reverse();
			sb.append(sb2).append("\n");
		}
		System.out.print(sb.toString());
	}
}