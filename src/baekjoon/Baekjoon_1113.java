package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 수영장 만들기
/*
 * 테두리는 물을 채울 수 없으므로 제외하고
 * 나머지 칸중에서 높이가 1인 곳 부터 연결되어 있는 구역을 bfs로 전부 탐색해서
 * 높이를 1씩 증가시켜 나가면 된다.
 * 구역을 탐색 중에 테두리가 연결되어 있거나, 높이가 낮은 부분이 근접해있으면 채울 수 없으므로 무시한다.
 */
public class Baekjoon_1113 {
	
	static int N,M,answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		int top = 1;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split("");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
				if(top<map[i][j]) top = map[i][j];
			}
		}
		
		for(int h=1; h<top; ++h) {
			visited = new boolean[N][M];
			for(int r=1; r<N-1; ++r) { // 테두리는 어차피 물을 채울 수 없으므로 무시
				for(int c=1; c<M-1; ++c) {
					if(!visited[r][c] && map[r][c]==h) { // 아직 물을 안 채웠고, 높이가 h이면
						fill(r,c,h); // (r,c)에 높이 h이면 물 채우기 시도
					}
				}
			}
		}
		System.out.println(answer);
	}
	
	public static void fill(int i, int j, int h) { // (r,c)에서 높이 h에 물을 채울 수 있는지 확인
		Queue<int[]> q = new LinkedList<int[]>();
		ArrayList<int[]> list = new ArrayList<int[]>();
		boolean flag = false; // 채울 수 있는지 확인, false면 가능 true면 불가능
		q.offer(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(r==0 || c==0 || r==N-1 || c==M-1) { // 테두리이면 물 채우기 불가
				flag = true; // 채우기 불가능 체크
				continue;
			}
			if(visited[r][c]) continue; // 방문체크
			visited[r][c] = true;
			list.add(new int[] {r,c});
			for(int d=0; d<4; ++d) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc]) continue; // 경계 및 방문
				if(map[r][c]>map[nr][nc]) { // 옆칸이 높이가 낮으면
					flag = true; // 채우기 불가능
				}
				if(map[r][c]==map[nr][nc]) { // 높이가 같은 칸만 추가
					q.offer(new int[] {nr,nc});
				}
			}
		}
		if(!flag) {
			for(int[] cur : list) {
				map[cur[0]][cur[1]]++; // 물 높이 1씩 채우기
				answer++; // 정답 증가
			}
		}
	}
}