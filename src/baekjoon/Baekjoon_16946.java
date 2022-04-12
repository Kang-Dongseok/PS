package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 벽 부수고 이동하기 4
/*
 * 0이 붙어있는 구역의 0 갯수를 센 후, 인접한 1에 0의 갯수를 더한다.
 * 이 때 여러방향으로 인접해있으면 한번만 더해주는것이 중요하다.
 * 마지막으로 1의 위치에 1을 더한다.
 */
public class Baekjoon_16946 {

	static int N,M,cnt;
	static char[][] map;
	static int[][] answer;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	static boolean[][] visited;
	static Set<Integer> posSet;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		posSet = new HashSet();
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		answer = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]=='0' && !visited[i][j]) { // 방문안한 0 발견하면
					cnt=0; // 초기화
					posSet.clear();
					dfs(i,j);
					for(int pos : posSet) {
						int r = pos/M;
						int c = pos%M;
						answer[r][c] += cnt;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]=='1') sb.append((answer[i][j]+1)%10);
				else sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dfs(int r, int c) {
		visited[r][c] = true;
		cnt++; // 0의 갯수 증가
		
		for(int d=0; d<4; ++d) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]) continue; // 경계 및 방문체크
			if(map[nr][nc]=='1') { // 벽이면
				posSet.add(nr*M+nc);
			}else { // 벽이 아니면
				dfs(nr,nc);
			}
		}
	}
}