package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소형기관차
/*
 * dp[i][j]는 i번째 부분합까지 중에서 j+1개를 선택한 최댓값을 저장하는 방식으로 해결하였다.
 * 여기서 총 3개만 선택하므로 j는 0~2까지 이다.
 */
public class Baekjoon_2616 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] train = new int[N];
		String[] line = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			train[i] = Integer.parseInt(line[i]);
		}
		int K = Integer.parseInt(br.readLine());
		int len = N-K+1; // subtotal 배열의 길이
		int[] subtotal = new int[len]; // 인덱스부터 차례대로 K개를 합한 값을 저장
		int fromIdx = 0;
		int toIdx = K-1;
		for(int i=0; i<=toIdx; ++i) {
			subtotal[0] += train[i];
		}
		for(int i=1; i<len; ++i) {
			toIdx++;
			subtotal[i] = subtotal[i-1]-train[fromIdx]+train[toIdx];
			fromIdx++;
		}
		int[][] dp = new int[len][3];
		dp[0][0] = subtotal[0];
		for(int i=1; i<len; ++i) {
			dp[i][0] = Math.max(dp[i-1][0],subtotal[i]);
		}
		dp[K][1] = subtotal[0]+subtotal[K];
		for(int i=K+1; i<len; ++i) {
			dp[i][1] = Math.max(dp[i-1][1],subtotal[i]+dp[i-K][0]);
		}
		dp[2*K][2] = subtotal[0]+subtotal[K]+subtotal[2*K];
		for(int i=2*K+1; i<len; ++i) {
			dp[i][2] = Math.max(dp[i-1][2], subtotal[i]+dp[i-K][1]);
		}
		System.out.println(dp[len-1][2]);
	}
}
