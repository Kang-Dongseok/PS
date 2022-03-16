package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 다리 만들기2
/*
 * 각 섬들을 하나의 정점으로 묶기 위해 하나의 번호로 통일하는 그루핑을 함.
 * 그 이후 각 섬으로부터 가능한 간선들을 pq에 넣어 kruskal 알고리즘을 적용하여 계산.
 */
public class Baekjoon_17472 {

	static int N,M;
	static int result;
	static int islandNum = 1; // 섬에 매길 번호
	static int[][] map; // 지도
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	// 인접행렬은 원래 섬의 갯수만큼 선언하는게 best지만 최대 6개 섬이므로 메모리 낭비가 심하지 않아서 고정값으로 선언
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		numbering(); // 각 섬들에 번호붙이기
		findPath(); // 각 섬들로부터 다른 섬들까지의 간선들을 pq에 담기
		calcPath(); // 신장트리의 가중치 합 계산후 출력
		
	}
	private static void calcPath() {
		make(); // find, union을 하기 위한 작업
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.start, edge.end)) {
				result += edge.weight;
				if(++cnt==islandNum-2) { // 간선을 정점갯수-1 개 만큼 선택했으면 종료, 섬이 2번부터 시작이므로 2를 뺌
					System.out.println(result);
					return;
				}
			}
		}
		// 간선을 다 체크해도 신장트리를 만들지 못하면 -1 출력 후 종료
		System.out.println(-1);
	}
	private static void findPath() { // pq에 간선들을 저장하는 메소드
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0) func(i,j);
			}
		}
	}
	private static void func(int r, int c) {
		int start = map[r][c]; // 시작 섬의 번호
		int nr,nc;
		for(int i=0; i<4; i++) {
			int cnt = 0; // 간선길이 측정 용도
			nr = r+dir[i][0]; // 4방 계속 직진 탐색
			nc = c+dir[i][1];
			while(isValid(nr, nc)) { // (r,c)을 기준으로 위쪽 간선
				if(map[nr][nc]==start) break; // 섬 테두리가 아니면 종료
				if(map[nr][nc]!=0) { // 다른 섬을 만나면
					if(cnt>1) { // 간선의 길이가 1보다 크면
						int end = map[nr][nc]; // 도착 섬의 번호
						int weight = cnt; // 간선의 길이
						pq.add(new Edge(start, end, weight));
					}
					break; // 간선의 길이가 1이하이면 간선을 pq에 추가하지 않고 종료
				}
				nr += dir[i][0];
				nc += dir[i][1];
				cnt++;
			}
		}
	}
	private static void numbering() { // dfs를 이용하여 섬들에 번호를 매기는 메소드, 2번 ~ (최대)7번
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					++islandNum; // 이번 차례에 발견될 섬의 번호, 2번부터 시작
					dfs(i,j); // 1인곳을 발견하면 해당 섬 전체를 번호를 매김
				}
			}
		}
	}
	private static void dfs(int r, int c) { // dfs를 이용
		
		if(map[r][c]!=1) return; // 땅이 아니면 종료
		
		map[r][c] = islandNum; // 땅 하나하나 섬의 번호 붙이기
		
		int nr,nc;
		for(int i=0; i<4; i++) {
			nr = r+dir[i][0];
			nc = c+dir[i][1];
			if(isValid(nr,nc)) dfs(nr,nc); // 상,우,하,좌
		}
		
	}
	private static boolean isValid(int r, int c) { // 경계 체크
		if(r>=0 && r<N && c>=0 && c<M) return true;
		return false;
	}
	
	/*********kruskal 알고리즘 사용하기 위한 준비*********/
	static class Edge implements Comparable<Edge>{
		
		int start,end,weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int[] parents; // 부모원소를 관리(트리처럼 사용)
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	
	private static void make() {
		parents = new int[islandNum+1];
		for(int i=0; i<islandNum+1; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false; // 이미 같은 집합이므로 합치지 않음
		
		parents[bRoot] = aRoot;
		return true;
	}
}
