package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 로봇 청소기
public class Baekjoon_4991 {
	
	static int R,C;
	static int[][] dist;
	static char[][] map;
	static ArrayList<int[]> pos;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	loop:while(true) {
			String[] line = br.readLine().split(" ");
			R = Integer.parseInt(line[1]);
			C = Integer.parseInt(line[0]);
			if(R==0) break;
			map = new char[R][C];
			for(int i=0; i<R; ++i) {
				map[i] = br.readLine().toCharArray();
			}
			pos = new ArrayList<int[]>();
			int cnt = 0; // 더러운 칸의 수
			for(int r=0; r<R; ++r) {
				for(int c=0; c<C; ++c) {
					if(map[r][c]=='*') {
						cnt++;
						pos.add(new int[] {r,c});
					}else if(map[r][c]=='o') pos.add(0,new int[] {r,c});
				}
			}
			dist = new int[cnt+1][cnt+1]; // 0인덱스는 로봇위치, dist[i][j]는 i번 칸과 j번 칸의 거리
			for(int i=0,size=cnt+1; i<size-1; ++i) {
				for(int j=i+1; j<size; ++j) {
					dist[i][j] = calcDistance(pos.get(i),pos.get(j));
					if(dist[i][j]==-1) { // 이동불가이면
						sb.append(-1).append("\n");
						continue loop;
					}
					dist[j][i] = dist[i][j];
				}
			}
			int[] orders = new int[cnt];
			for(int i=0; i<cnt; ++i) {
				orders[i] = i+1; // 1~cnt까지 순번 저장
			}
			
			int answer = Integer.MAX_VALUE;
			do {
				int sum = dist[0][orders[0]];
				for(int i=0; i<cnt-1; ++i) {
					int from = orders[i];
					int to = orders[i+1];
					sum += dist[from][to];
				}
				if(answer>sum) answer=sum; // 최솟값 갱신
			}while(np(orders));
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static int calcDistance(int[] from, int[] to) {
		int dist = 0; // from부터 to까지의 거리
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(from);
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				if(visited[r][c]) continue;
				if(r==to[0] && c==to[1]) { // 도착지
					return dist;
				}
				visited[r][c] = true;
				for(int d=0; d<4; ++d) {
					int nr = r+dir[d][0];
					int nc = c+dir[d][1];
					if(nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc]) continue;
					if(map[nr][nc]!='x') q.offer(new int[] {nr,nc}); // 벽이 아니면 큐에 삽입
				}
			}
			dist++;
		}
		return -1; // 경로가 없으면 -1
	}
	
	public static boolean np(int[] numbers) {
		int N = numbers.length;
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) i--;
		if(i==0) return false;
		
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) j--;
		swap(numbers,i-1,j);
		
		int k = N-1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		return true;
	}
	
	public static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
}