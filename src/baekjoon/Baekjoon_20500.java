package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Ezreal 여눈부터 가네 ㅈㅈ
/*
 * dp[N][idx] = N번째자리까지 숫자중 3으로 나누었을때 나머지가 idx인 갯수
 */
public class Baekjoon_20500 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 1000000007;
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][3];
		dp[1][2] = 1; // 1의 자리는 무조건 5로 고정
		for(int i=2; i<N+1; ++i) {
			dp[i][0] = (dp[i-1][2]+dp[i-1][1])%MOD;
			dp[i][1] = (dp[i-1][0]+dp[i-1][2])%MOD;
			dp[i][2] = (dp[i-1][1]+dp[i-1][0])%MOD;
		}
		System.out.println(dp[N][0]);
	}
}
