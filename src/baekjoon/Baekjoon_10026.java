package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 적록색약
public class Baekjoon_10026 {

	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		String line;
		for(int i=0; i<N; ++i) {
			line = br.readLine();
			map[i] = line.toCharArray();
		}
		int count = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(!visited[i][j]) {
					count++;
					dfs(i,j,map[i][j]);
				}
			}
		}
		System.out.print(count+" ");
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(map[i][j]=='G') map[i][j]='R'; // 초록을 빨강으로 바꾸기
			}
		}
		
		count = 0; // 초기화
		visited = new boolean[N][N]; // 초기화
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(!visited[i][j]) {
					count++;
					dfs(i,j,map[i][j]);
				}
			}
		}
		System.out.println(count);
	}
	
	public static void dfs(int r, int c, char ch) {
		
		visited[r][c] = true;
		
		for(int d=0; d<4; ++d) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue; // 경계체크
			if(!visited[nr][nc] && map[nr][nc]==ch) dfs(nr,nc,ch); // 방문 및 같은 알파벳 체크
		}
	}
}