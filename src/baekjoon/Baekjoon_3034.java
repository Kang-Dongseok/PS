package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 앵그리 창영
public class Baekjoon_3034 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int W = Integer.parseInt(str[1]);
		int H = Integer.parseInt(str[2]);
		int len = W*W+H*H;
		for(int i=0; i<N; ++i) {
			int n = Integer.parseInt(br.readLine());
			if(n*n>len) sb.append("NE").append("\n");
			else sb.append("DA").append("\n");
		}
		System.out.println(sb.toString());
	}
}