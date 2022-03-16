package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 내리막 길
/*
 * dfs와 dp를 동시에 적용하는 문제이다.
 * 어느정도 감은 잡혔지만 정확한 구현이 떠오르지가 않아서 구글링을 하여 참고했다.
 * dp가 아직 부족한 것 같아서 더 공부해야겠다고 느꼈다.
 */
public class Baekjoon_1520 {

	static int N,M;
	static int[][] map,dp;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
				dp[i][j] = -1; // 경로의 갯수가 0개인 것과 구분하기 위해 -1로 전부 초기화
			}
		}
		
		System.out.println(dfs(0,0));
	}
	
	public static int dfs(int r, int c) {
		if(r==M-1 && c==N-1) { // 도착지에 도달하면 1리턴
			return 1;
		}
		
		if(dp[r][c]==-1) { // 아직 확인하지 않은 곳이면
			dp[r][c] = 0; // 0으로 초기화
			
			for(int d=0; d<4; ++d) {
				int nr = r+dx[d];
				int nc = c+dy[d];
				if(nr<0 || nc<0 || nr>=M || nc>=N) continue;
				
				if(map[r][c]>map[nr][nc]) {
					dp[r][c] += dfs(nr, nc);
				}
			}
		}
		return dp[r][c];
	}
}
