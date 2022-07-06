package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 경찰차
/*
 * 구글링하여 해결하였다.
 * dp[i][j] = 1번차가 i번째, 2번차가 j번째 사건위치에 도달했을 때, 마지막까지 해결하는 최솟값이다.
 * 즉 dp[0][0]을 구하기위해 dp[0][0]에서부터 재귀 방식으로 Top-Down 방식으로 해결한다.
 * dp배열을 어떤식으로 설정하는지 자체가 상당히 어려웠다.
 */
public class Baekjoon_2618 {

	static int N,W;
	static int[][] dp;
	static int[][] pos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		W = Integer.parseInt(br.readLine());
		dp = new int[W+1][W+1]; // N이 아니라 W의 수에 맞춰 초기화
		pos = new int[W+1][2];
		for(int i=1; i<=W; ++i) { // 인덱스 1부터
			String[] str = br.readLine().split(" ");
			pos[i][0] = Integer.parseInt(str[0]);
			pos[i][1] = Integer.parseInt(str[1]);
		}
		sb.append(solve(1, 0, 0)).append("\n");
		
		int idx1 = 0; // 현재 1번차의 인덱스
		int idx2 = 0; // 현재 2번차의 인덱스
		for(int i=1; i<=W; ++i) {
			int moveDist1 = getDist(1, idx1, i); // 1번차가 i번째 사건 위치까지의 거리
			
			if(dp[idx1][idx2] - moveDist1 == dp[i][idx2]) {
				idx1 = i;
				sb.append(1).append("\n");
			}else {
				idx2 = i;
				sb.append(2).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static int solve(int eventIdx, int idx1, int idx2) {
		if(eventIdx>W) { // 사건의 인덱스가 사건 수보다 크면 종료
			return 0;
		}
		
		if(dp[idx1][idx2]!=0) { // 계산이 되어 있으면
			return dp[idx1][idx2];
		}
		
		int moveDist1 = solve(eventIdx+1, eventIdx, idx2) + getDist(1, idx1, eventIdx);
		int moveDist2 = solve(eventIdx+1, idx1, eventIdx) + getDist(2, idx2, eventIdx);
		
		return dp[idx1][idx2] = Math.min(moveDist1, moveDist2);
	}
	
	public static int getDist(int type, int from, int to) {
		if(from==0) {
			if(type==1) { // 1번째 차이면 {1,1}에서 to까지
				return Math.abs(pos[to][0]-1)+Math.abs(pos[to][1]-1);
			}else { // 2번째 차이면 {N,N}에서 to까지
				return Math.abs(pos[to][0]-N)+Math.abs(pos[to][1]-N);
			}
		}
		return Math.abs(pos[from][0]-pos[to][0])+Math.abs(pos[from][1]-pos[to][1]);
	}
}