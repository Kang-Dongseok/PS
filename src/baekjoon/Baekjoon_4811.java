package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 알약
/*
 * dp문제인걸 바로 눈치챘지만, 풀이를 생각해내는데 거의 2시간 걸렸다ㅠㅠ
 * 2차원배열을 생성하여 풀었지만, 구글링을 참조하니 1차원배열을 생성해서도 해결하는 방법을 알았다.
 * 근데 왜 내 풀이가 시간이 더 빠르지...? ㅎㅎ
 * dp의 세계란 정말 끝이 없는 것 같다...
 * 내 풀이법: dp[a][b] => a번째 칸까지 b개를 채우는 경우의 수 => W가 a번 나올 때 H를 b개 채우는 경우의 수
 * dp[a][b] = dp[a-1][1] + dp[a-1][2] + dp[a-1][3] + ... + dp[a-1][b] (a>b일 때)
 * dp[a][b] = dp[a][b-1] (a==b일 때)
 */
public class Baekjoon_4811 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] dp = new long[31][31];
		for(int i=1; i<=30; ++i) {
			dp[i][1] = i; // 1열 전부 초기화
		}
		for(int i=2; i<=30; ++i) {
			for(int j=2; j<i; ++j) {
				dp[i][j] = dp[i-1][j]+dp[i][j-1]; // 윗칸+왼쪽칸
			}
			dp[i][i]=dp[i][i-1]; // i==j일때는 왼쪽칸과 같은 값
		}
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) break; // 종료
			sb.append(dp[N][N]).append("\n");
		}
		System.out.println(sb.toString());
		
		/* 구글링 참고한 풀이
		long[] dp = new long[31];
		dp[0]=1;
		dp[1]=1;
		dp[2]=2;
		for(int i=3; i<=30; ++i) {
			long count = 0;
			for(int j=0; j<i; ++j) {
				count += dp[j]*dp[i-1-j];
			}
			dp[i] = count;
		}
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) break; // 종료
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb.toString());
		*/
	}
}