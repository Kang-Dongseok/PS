package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
// 달리기
public class Baekjoon_16930 {

	static int r1,c1,r2,c2;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		map = new char[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		str = br.readLine().split(" ");
		r1 = Integer.parseInt(str[0])-1;
		c1 = Integer.parseInt(str[1])-1;
		r2 = Integer.parseInt(str[2])-1;
		c2 = Integer.parseInt(str[3])-1;
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r1,c1});
		
		int answer = -1; // 답이 없을 경우 -1
		int dist = 0;
		
	loop:while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				System.out.println(r+","+c);
//				if(visited[r][c]) continue; // 이미 방문했으면
				visited[r][c] = true; // 방문처리
				
				if(r==r2 && c==c2) { // 마지막칸에 도착했으면
					answer = dist; // 이동거리 저장
					break loop;
				}
				
				for(int d=0; d<4; ++d) {
					for(int i=1; i<=K; ++i) {
						int nr = r+i*dir[d][0];
						int nc = c+i*dir[d][1];
						if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]=='#') break; // 범위밖 또는 벽이면 종료
						if(!visited[nr][nc]) {
							visited[nr][nc]=true; // 방문 체크
							q.offer(new int[] {nr,nc});
						}
					}
				}
			}
			dist++; // 이동거리 증가
		}
		System.out.println(answer);
	}
}