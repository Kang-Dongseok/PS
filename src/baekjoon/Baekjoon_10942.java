package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팰린드롬?
public class Baekjoon_10942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] nums = new int[N+1];
		boolean[][] dp = new boolean[N+1][N+1];
		for(int i=1; i<=N; ++i) {
			nums[i] = Integer.parseInt(str[i-1]);
		}
		
		for(int i=1; i<N; ++i) {
			dp[i][i] = true; // 1개는 true;
			if(nums[i]==nums[i+1])dp[i][i+1] = true; // 2개는 다음거와 같으면 true
		}
		dp[N][N] = true; // 1개는 true;
		for(int i=N-2; i>0; --i) {
			for(int j=i+2; j<=N; ++j) {
				if(dp[i+1][j-1] && nums[i]==nums[j]) dp[i][j] = true;
			}
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);
			if(dp[from][to]) sb.append(1);
			else sb.append(0);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}