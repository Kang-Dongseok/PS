package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 행렬 곱셈 순서
public class Baekjoon_11049 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for(int i=0; i<N; ++i) {
			String[] line = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(line[0]);
			arr[i][1] = Integer.parseInt(line[1]);
		}
		int[][] dp = new int[N][N];
		for(int c=1; c<N; ++c) {
			for(int r=c-1; r>=0; --r) {
				int min = Integer.MAX_VALUE;
				for(int idx=c; idx>r; --idx) {
					min = Math.min(min, dp[r][idx-1]+dp[idx][c]+arr[r][0]*arr[idx][0]*arr[c][1]);
				}
				dp[r][c] = min;
			}
		}
		System.out.println(dp[0][N-1]);
	}
}