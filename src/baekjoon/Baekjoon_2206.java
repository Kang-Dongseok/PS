package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
// 벽 부수고 이동하기
/*
 * 벽을 부쉈는지 아닌지를 체크하면서 동시에 방문체크를 하기 위해
 * 3차원 boolean배열 3번째 인덱스를 벽을 부순 횟수의 상태를 체크하는데 사용한다.
 */
public class Baekjoon_2206 {

	static char[][] map;
	static boolean[][][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		map = new char[N][M];
		visited = new boolean[N][M][2];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {0,0,0});
		
		int answer = -1;
		int depth = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			depth++; // 이동거리
			while(size-->0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				int state = cur[2];
				if(visited[r][c][state]) continue; // 이미 방문했으면
				visited[r][c][state] = true; // 방문처리
				
				if(r==N-1 && c==M-1) { // 마지막칸에 도착했으면
					answer = depth; // 이동거리 저장
					break;
				}

				for(int d=0; d<4; ++d) {
					int nr = r+dir[d][0];
					int nc = c+dir[d][1];
					if(nr<0 || nr>=N || nc<0 || nc>=M) continue; // 경계체크
					if(map[nr][nc]=='0') { // 다음칸이 벽 없으면
						if(visited[nr][nc][0]) continue; // 방문체크
						q.offer(new int[] {nr,nc,state});
					}else if(map[nr][nc]=='1' && state==0) { // 다음칸 벽이고 이전에 벽 안부쉈으면
						if(visited[nr][nc][1]) continue; // 방문체크
						q.offer(new int[] {nr,nc,1}); // 상태를 벽 부순걸로 바꿈
					}
				}
			}
			if(answer>0) break; // 최단거리 저장했으면 종료
		}
		System.out.println(answer);
	}
}