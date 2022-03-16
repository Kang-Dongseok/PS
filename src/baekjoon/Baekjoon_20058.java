package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 마법사 상어와 파이어스톰
/*
 * 덩어리의 크기를 구하는 과정은 bfs를 이용하였다.
 * 회전시킬 때 마다 2차원 배열을 새로 생성하면 공간을 많이 차지하므로
 * map[bit][][] 구조를 이용하여 2차원 배열 2개를 사용하여 공간복잡도를 줄이려고 노력했다. 
 */
public class Baekjoon_20058 {

	static int N,Q,bit,mass;
	static int[][][] map; // map[0또는1][][]
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = (int)Math.pow(2, Integer.parseInt(str[0]));
		Q = Integer.parseInt(str[1]);
		bit=0; // bit가 0이면 map[0][][]에, 1이면 map[1][][]에 가장 최근의 얼음을 저장
		map = new int[2][N][N];
		
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[bit][i][j] = Integer.parseInt(str[j]);
			}
		}
		str = br.readLine().split(" ");
		for(int i=0; i<Q; ++i) {
			int L = Integer.parseInt(str[i]);
			fireStorm(L);
		}
		
		int sum = 0; // 얼음들의 합 구하기
		for(int[] arr : map[bit]) {
			for(int a : arr) {
				sum+=a;
			}
		}
		
		visited = new boolean[N][N];
		mass = 0; // 최대 덩어리의 크기
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(!visited[i][j]) {
					if(map[bit][i][j]!=0) bfs(i,j);
				}
			}
		}
		System.out.println(sum);
		System.out.println(mass);
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0; d<4; ++d) {
				int nr = cur[0]+dir[d][0];
				int nc = cur[1]+dir[d][1];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(map[bit][nr][nc]==0) continue;
				if(!visited[nr][nc]) {
					visited[nr][nc]=true;
					q.offer(new int[] {nr,nc});
					cnt++;
				}
			}
		}
		if(mass<cnt) mass=cnt;
	}
	
	public static void fireStorm(int L) {
		int size = (int)Math.pow(2, L); // 부분 격자의 한 변의 길이
		bit ^= 1; // XOR 비트연산을 비용하여 2차원 배열 2개를 번갈아 사용
		for(int i=0; i<N; i+=size) {
			for(int j=0; j<N; j+=size) {
				rotateRect90(i,j,size);
			}
		}
		// 회전이 끝난 후
		decreaseIce();
	}
	
	public static void rotateRect90(int r, int c, int size) {
		for(int i=0; i<size; ++i) {
			for(int j=0; j<size; ++j) {
				map[bit][r+i][c+j] = map[bit^1][r+size-j-1][c+i]; // map1을 회전한것을 map0에 저장
			}
		}
	}
	
	public static void decreaseIce() {
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(cntOfIceAround(i,j)<3) q.offer(new int[] {i,j}); // 감소할 얼음의 위치들을 저장
			}
		}
		// 얼음들 감소하기
		for(int[] pos : q) {
			if(map[bit][pos[0]][pos[1]]>0) map[bit][pos[0]][pos[1]]--;
		}
	}
	
	public static int cntOfIceAround(int r, int c) {
		int cnt = 0;
		for(int d=0; d<4; ++d) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(map[bit][nr][nc] > 0) cnt++;
		}
		return cnt;
	}
}