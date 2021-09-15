package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 말이 되고픈 원숭이
public class Baekjoon_1600 {

	static int K,H,W;
	static int result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dr = {-1,0,1,0}; // 상우하좌 
	static int[] dc = {0,1,0,-1}; 
	static int[] hr = {-2,-2,-1,1,2,2,1,-1}; // 말의 움직임 시계방향
	static int[] hc = {-1,1,2,2,1,-1,-2,-2};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		W = Integer.parseInt(str[0]);
		H = Integer.parseInt(str[1]);
		map = new int[H][W];
		for(int i=0; i<H; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<W; ++j) {
				map[i][j]=Integer.parseInt(str[j]);
			}
		}
		bfs();
		if(result==Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(result);
	}
	
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][][] visited;
	
	public static void bfs() {
		visited = new boolean[H][W][K+1];
		q.offer(new int[] {0,0,0,0}); // {행,열,이동횟수,말처럼이동횟수}
		visited[0][0][0] = true;
		
		int[] current;
		while(!q.isEmpty()) {
			current = q.poll();
			int r = current[0]; // 행
			int c = current[1]; // 열
			int move = current[2]; // 움직인 횟수
			int hMove = current[3]; // 말처럼 움직인 횟수
			if(r==H-1 && c==W-1) {
				result = Math.min(move, result); // 도착지점이면 최솟값 갱신
			}
			int nr,nc;
			for(int i=0; i<4; ++i) { // 인접칸 움직이기
				nr = r+dr[i];
				nc = c+dc[i];
				if(isValid(nr, nc, hMove)) {
					q.offer(new int[] {nr,nc,move+1,hMove}); // 좌표와 이동횟수만 갱신
					visited[nr][nc][hMove] = true;
				}
			}
			if(hMove<K) { // 말처럼 이동할 수 있는 횟수가 남아 있으면
				for(int i=0; i<8; ++i) { // 말처럼 움직이기
					nr = r+hr[i];
					nc = c+hc[i];
					if(isValid(nr, nc, hMove+1)) {
						q.offer(new int[] {nr,nc,move+1,hMove+1}); // 좌표, 이동횟수, 말처럼이동횟수 갱신
						visited[nr][nc][hMove+1] = true;
					}
				}
			}
		}
	}
	public static boolean isValid(int r, int c, int k) {
		if(r>=0 && c>=0 && r<H && c<W && map[r][c]!=1 && !visited[r][c][k]) return true;
		return false;
	}
}