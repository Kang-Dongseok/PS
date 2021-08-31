package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


// 별자리 만들기
/*
 * 1774번 문제와 거의 같은 유형이다. Kruskal 알고리즘으로 해결하였다.
 * 시간 복잡도: O((N^2)*(log(N^2)))
 */
public class Baekjoon_4386 {
	
	static class Edge implements Comparable<Edge> {
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
	
	static int n;
	static int[] parents;
	static double[][] stars; // 별들의 좌표 저장할 배열
	static PriorityQueue<Edge> edgePQ;
	static double result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// 별들의 좌표 저장
		stars = new double[n][2];
		for(int i=0; i<n; ++i) {
			String[] line = br.readLine().split(" ");
			stars[i][0] = Double.parseDouble(line[0]);
			stars[i][1] = Double.parseDouble(line[1]);
		}
		// 간선의 정보들을 PQ에 저장
		edgePQ = new PriorityQueue<>();
		for(int i=0; i<n; ++i) {
			for(int j=i+1; j<n; ++j) {
				double dx = stars[i][0]-stars[j][0]; // 행 거리차이
				double dy = stars[i][1]-stars[j][1]; // 열 거리차이
				double weight = Math.sqrt(dx*dx + dy*dy);
				edgePQ.offer(new Edge(i, j, weight));
			}
		}
		
		make();
		
		int cnt = 0; // 연결한 간선의 갯수
		while(!edgePQ.isEmpty()) {
			Edge edge = edgePQ.poll();
			if(union(edge.start, edge.end)) {
				result += edge.weight;
				if(++cnt==n-1) break; // n-1개의 간선을 선택하면 n개의 별들을 연결시켰으므로 종료
			}
		}
		System.out.printf("%.2f",result);
	}
	private static void make() {
		parents = new int[n];
		for(int i=0; i<n; ++i) {
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
		if(aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
}
