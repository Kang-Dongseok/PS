package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 우주신과의 교감
/*
 * MST 문제이므로 Kruskal 또는 Prim 알고리즘으로 해결할 수 있다고 생각했다.
 * Kruskal로 시도해보았다.
 */
public class Baekjoon_1774 {

	static class Edge implements Comparable<Edge>{
		int start,end;
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
		
	}
	
	static int N,M;
	static double[][] gods; // 우주신들의 좌표를 저장
	static PriorityQueue<Edge> edgePQ; // 간선들의 정보를 저장
	static int[] parents;
	static double result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		gods = new double[N][2];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			gods[i][0] = Integer.parseInt(str[0]);
			gods[i][1] = Integer.parseInt(str[1]);
		}
		edgePQ = new PriorityQueue<>();
		for(int i=0; i<N; ++i) {
			for(int j=i+1; j<N; ++j) {
				double dx = gods[i][0]-gods[j][0]; // 행의 차이
				double dy = gods[i][1]-gods[j][1]; // 열의 차이
				double weight = Math.sqrt(dx*dx + dy*dy);
				edgePQ.offer(new Edge(i, j, weight));
			}
		}
		make();
		int cnt = 0; // 이미 연결된 신들의 수
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			// 이미 연결된 신들과의 간선 합치기, 인덱스 = 번호 -1
			if(union(Integer.parseInt(str[0])-1, Integer.parseInt(str[1])-1)) cnt++;
		}
		while(!edgePQ.isEmpty()) {
			Edge edge = edgePQ.poll();
			if(union(edge.start, edge.end)) {
				result += edge.weight;
				if(++cnt == N-1) break;	 // 신장트리 완성
			}
		}
		
		System.out.printf("%.2f",result);
	}
	
	private static void make() {
		parents = new int[N];
		for(int i=0; i<N; ++i) {
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
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
}