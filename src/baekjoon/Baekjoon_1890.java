package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 점프
public class Baekjoon_1890 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i=0; i<N; ++i) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		boolean[][] canJump = new boolean[N][N];
		long[][] dp = new long[N][N];
		
		canJump[0][0] = true;
		dp[0][0] = 1;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(canJump[i][j] && map[i][j]!=0) { // 갈 수 있는 곳이며 0(종착점)이 아니면
					int dist = map[i][j];
					if(i+dist<N) {
						canJump[i+dist][j] = true;
						dp[i+dist][j] += dp[i][j];
					}
					if(j+dist<N) {
						canJump[i][j+dist] = true;
						dp[i][j+dist] += dp[i][j];
					}
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
}