package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 토마토
public class Baekjoon_7576 {
	
	static int N,M,day;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] map;
	static Queue<int[]> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		map = new int[N][M];
		q = new LinkedList<int[]>();
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j]==1) q.add(new int[] {i,j}); // 익은 토마토의 좌표 저장
			}
		}

		bfs();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(--day); // 시작부터 1일이므로 하루를 빼준다.
	}
	private static void bfs() {
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] current = q.poll();
				int r = current[0];
				int c = current[1];
				for(int i=0; i<4; i++) {
					int nr = r+dr[i];
					int nc = c+dc[i];
					if(isValid(nr, nc)) {
						map[nr][nc]=1; // 토마토 익히기
						q.offer(new int[] {nr,nc}); // 새로 익은 토마토 좌표를 큐에 넣기
					}
				}
			}
			day++; // 하루 증가
		}
	}
	private static boolean isValid(int r, int c) { // 경계 및 토마토 체크
		if(r>=0 && c>=0 && r<N && c<M && map[r][c]==0) return true; // 안익은 토마토이면 true
		return false;
	}
}