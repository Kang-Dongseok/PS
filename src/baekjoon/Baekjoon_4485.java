package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// 녹색 옷 입은 애가 젤다지?
/*
 * dfs + memoization(dp?)
 */
public class Baekjoon_4485 {
	
	static int N;
	static int[][] map,D;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str;
		int tc=0;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			map = new int[N][N];
			for(int i=0; i<N; ++i) {
				str = br.readLine().split(" ");
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			D = new int[N][N];
			for(int i=0; i<N; ++i) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			visited = new boolean[N][N];
			D[0][0] = map[0][0]; // 시작위치의 초기화 값
			dfs(0,0);
			sb.append("Problem ").append(++tc).append(": ").append(D[N-1][N-1]).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static void dfs(int r, int c) {
		if(D[r][c]>=D[N-1][N-1]) return; // 도착지의 최솟값보다 이상이면 가지치기
		if(r==N-1 && c==N-1) return; // 도착지에 도달하면 종료
		
		visited[r][c] = true;
		for(int i=0; i<4; ++i) { // 4방 탐색
			int nr = r+dir[i][0];
			int nc = c+dir[i][1];
			if(isValid(nr,nc) && D[nr][nc] > D[r][c]+map[nr][nc]) { // 이전의 최솟값보다 더 작으면 갱신
				D[nr][nc] = D[r][c]+map[nr][nc];
				dfs(nr,nc);
			}
		}
		visited[r][c] = false;
	}
	public static boolean isValid(int r, int c) { // 경계 및 방문 체크
		if(r>=0 && r<N && c>=0 && c<N && !visited[r][c]) return true;
		return false;
	}
}
