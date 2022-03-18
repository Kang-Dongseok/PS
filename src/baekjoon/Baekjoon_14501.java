package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 퇴사
public class Baekjoon_14501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		int[] dp = new int[N];
		String[] line;
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			T[i] = Integer.parseInt(line[0]);
			P[i] = Integer.parseInt(line[1]);
		}
		for(int i=0; i<N; ++i) {
			int end = i+T[i]-1; // 끝나는 날짜
			int subtotal = P[i]; // 현재 날짜에서의 값
			if(i!=0) subtotal += dp[i-1]; // 이전까지의 최댓값을 더함
			if(end<N) {
				for(int j=end; j<N; ++j) {
					dp[j] = Math.max(dp[j], subtotal);
				}
			}
		}
		System.out.println(dp[N-1]);
	}
}