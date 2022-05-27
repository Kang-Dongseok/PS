package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 연속합
/*
 * dp[2][N]
 * 0행은 누적합, 1행은 누적합 중 최솟값
 * dp[0][i] - dp[1][i]의 최댓값을 구하면 된다.
 */
public class Baekjoon_1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int max = Integer.MIN_VALUE;
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
			max = Math.max(max, arr[i]);
		}
		if(max<0) { // 음수만 있으면
			System.out.println(max); // 1개만 선택 후 종료
			return;
		}
		int[][] dp = new int[2][N];
		dp[0][0] = arr[0];
		if(arr[0]<0) dp[1][0] = arr[0]; // 음수이면 저장
		
		for(int i=1; i<N; ++i) {
			dp[0][i] = dp[0][i-1]+arr[i];
			dp[1][i] = Math.min(dp[1][i-1],dp[0][i]);
		}
		
		for(int i=0; i<N; ++i) {
			int sum = dp[0][i]-dp[1][i];
			if(max<sum) max=sum;
		}
		System.out.println(max);
	}
}