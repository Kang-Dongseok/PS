package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 파도반 수열
public class Baekjoon_9461 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			if(N<=3) {
				sb.append(1+"\n");
				continue;
			}
			long[] D = new long[N];
			D[0] = D[1] = D[2] = 1;
			for(int i=3; i<N; ++i) {
				D[i] = D[i-2]+D[i-3];
			}
			sb.append(D[N-1]+"\n");
		}
		System.out.println(sb.toString());
	}
}