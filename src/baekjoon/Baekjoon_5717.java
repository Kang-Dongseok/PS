package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 상근이의 친구들
public class Baekjoon_5717 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String[] line = br.readLine().split(" ");
			int M = Integer.parseInt(line[0]);
			int F = Integer.parseInt(line[1]);
			if(M==0 && F==0) break;
			sb.append(M+F+"\n");
		}
		System.out.print(sb.toString());
	}
}