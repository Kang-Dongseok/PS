package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 통나무 옮기기
/*
 * 상태를 가로,세로 2가지로 나눠서 구분하여 bfs를 실행하면 된다.
 */
public class Baekjoon_1938 {

	static int N;
	static int startR,startC,startS; // 시작의 행,열,상태(0:가로, 1:세로)
	static int endR,endC,endS; // 도착
	static char[][] map;
	static boolean[][][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1}}; // 상,우,하,좌,대각선4방향
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2]; // [행][열][상태(0또는1)]
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray(); 
		}
	loop1:for(int i=0; i<N; ++i) { // 시작지점 찾기
			for(int j=0; j<N; ++j) {
				if(map[i][j]=='B') {
					if(j+1<N && map[i][j+1]=='B') { // 가로
						startR=i; startC=j+1; startS=0;
					}else { // 세로
						startR=i+1; startC=j; startS=1;
					}
					break loop1;
				}
			}
		}
	loop2:for(int i=0; i<N; ++i) { // 도착지점 찾기
			for(int j=0; j<N; ++j) {
				if(map[i][j]=='E') {
					if(j+1<N && map[i][j+1]=='E') { // 가로
						endR=i; endC=j+1; endS=0;
					}else { // 세로
						endR=i+1; endC=j; endS=1;
					}
					break loop2;
				}
			}
		}
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {startR,startC,startS});
		int depth = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				int s = cur[2];
				
				if(visited[r][c][s]) continue;
				visited[r][c][s] = true;
				if(r==endR && c==endC && s==endS) {
					System.out.println(depth);
					return;
				}
				// 4방탐색
				for(int d=0; d<4; ++d) {
					int nr = r+dir[d][0];
					int nc = c+dir[d][1];
					if(s==0) { // 가로이면
						if(nr<0 || nr>=N || nc<=0 || nc>=N-1 || map[nr][nc]=='1' || visited[nr][nc][s]) continue; // 경계 및 방문 체크
					}else { // 세로이면
						if(nr<=0 || nr>=N-1 || nc<0 || nc>=N || map[nr][nc]=='1' || visited[nr][nc][s]) continue; // 경계 및 방문 체크
					}
					if(canMove(nr,nc,s)) q.offer(new int[] {nr,nc,s});
				}
				boolean canRotate = true; // 회전가능 여부
				for(int d=0; d<8; ++d) {
					int nr = r+dir[d][0];
					int nc = c+dir[d][1];
					if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]=='1') {
						canRotate = false;
						break;
					}
				}
				// 회전
				if(canRotate && !visited[r][c][s^1]) { // 회전가능 및 미방문
					q.offer(new int[] {r,c,s^1}); // 제자리 회전
				}
			}
			depth++;
		}
		System.out.println(0);
	}
	
	public static boolean canMove(int r, int c, int s) {
		if(s==0) {
			if(map[r][c-1]=='1' || map[r][c+1]=='1') return false;
		}else {
			if(map[r-1][c]=='1' || map[r+1][c]=='1') return false;
		}
		return true;
	}
}