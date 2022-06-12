package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 성곽
public class Baekjoon_2234 {

	static int N,M;
	static int[][] map;
	static boolean[][][] path;
	static Map<Integer, Integer> cntMap;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상우하좌
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[M][N];
		path = new boolean[M][N][4];
		cntMap = new HashMap<Integer, Integer>();
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				int n = Integer.parseInt(str[j]);
				if(n<8) path[i][j][2]=true; // 아래쪽 열려있으면
				else n -= 8;
				if(n<4) path[i][j][1]=true; // 우
				else n -= 4;
				if(n<2) path[i][j][0]=true; // 상
				else n -= 2;
				if(n<1) path[i][j][3]=true; // 좌
			}
		}
		
		int cnt = 0; // 방의 갯수
		int max = 0; // 방 하나 최대 넓이
		int unionMax = 0; // 방 합친 최대 넓이
		for(int i=0; i<M; ++i) {
			for(int j=0; j<N; ++j) {
				if(map[i][j]==0) { // 아직 갯수 표시 안했으면
					cnt++;
					bfs(i,j,cnt);
				}
			}
		}
		for(int key : cntMap.keySet()) {
			max = Math.max(max, cntMap.get(key));
		}
		for(int i=0; i<M; ++i) {
			for(int j=0; j<N; ++j) {
				for(int d=0; d<4; ++d) {
					if(path[i][j][d]) continue; // 뚫려있으면 무시
					int ni = i+dir[d][0];
					int nj = j+dir[d][1];
					if(ni<0 || ni>=M || nj<0 || nj>=N) continue; // 경계체크
					if(map[i][j]!=map[ni][nj]) { // 서로 다른 구역이면
						int total = cntMap.get(map[i][j])+cntMap.get(map[ni][nj]);
						unionMax = Math.max(unionMax, total);
					}
				}
			}
		}
		System.out.println(cnt);
		System.out.println(max);
		System.out.println(unionMax);
	}
	
	public static void bfs(int i, int j, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i,j});
		boolean[][] visited = new boolean[M][N];
		ArrayList<int[]> list = new ArrayList<int[]>();
		int num = 0; // 칸의 갯수
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			list.add(new int[] {r,c});
			num++;
			
			for(int d=0; d<4; ++d) {
				if(!path[r][c][d]) continue; // 길이 없으면 무시
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(nr<0 || nr>=M || nc<0 || nc>=N || visited[nr][nc]) continue;
				q.offer(new int[] {nr,nc});
			}
		}
		for(int[] cur : list) {
			map[cur[0]][cur[1]] = cnt;
		}
		cntMap.put(cnt, num);
	}
}