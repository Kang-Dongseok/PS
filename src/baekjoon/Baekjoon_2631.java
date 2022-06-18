package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 줄세우기
public class Baekjoon_2631 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[N];
		dp[0] = 1;
		int max = 0;
		for(int i=1; i<N; ++i) {
			dp[i] = 1; // 수열 1로 초기화
			for(int j=0; j<i; ++j) {
				if(arr[j]<arr[i]) dp[i] = Math.max(dp[i], dp[j]+1);
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(N-max);
	}
}