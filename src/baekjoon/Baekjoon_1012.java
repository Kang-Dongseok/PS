package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 유기농 배추
public class Baekjoon_1012 {

	static int N,M,answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; ++t) {
			String[] line = br.readLine().split(" ");
			M = Integer.parseInt(line[0]);
			N = Integer.parseInt(line[1]);
			int K = Integer.parseInt(line[2]);
			answer=0; // 테케마다 초기화
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int k=0; k<K; ++k) {
				line = br.readLine().split(" ");
				int r = Integer.parseInt(line[1]);
				int c = Integer.parseInt(line[0]);
				map[r][c] = 1; // 배추 표시
			}
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<M; ++j) {
					if(map[i][j]==1) { // 배추가 있으면
						if(!visited[i][j]) { // 아직 확인하지 않았으면
							dfs(i,j);
							answer++;
						}
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dfs(int r, int c) {
		visited[r][c]=true;
		
		for(int d=0; d<4; ++d) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]) continue; // 경계체크 및 방문체크
			if(map[nr][nc]==1) dfs(nr,nc); // 다음 위치가 배추이면 진행
		}
	}
}