package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2*n 타일링 2
public class Baekjoon_11727 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n==1) {
			System.out.println(1);
		}else if(n==2) {
			System.out.println(3);
		}else {
			int[] dp = new int[n+1];
			final int MOD = 10007;
			dp[1] = 1;
			dp[2] = 3;
			for(int i=3; i<n+1; ++i) {
				dp[i] = (dp[i-2]*2 + dp[i-1])%MOD;
			}
			System.out.println(dp[n]);
		}
	}
}