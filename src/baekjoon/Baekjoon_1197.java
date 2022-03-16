package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 최소 스패닝 트리
/*
 * kruskal 알고리즘을 이용하여 어렵지 않게 풀었다.
 */
public class Baekjoon_1197 {

	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static Edge[] edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int V = Integer.parseInt(line[0]);
		int E = Integer.parseInt(line[1]);
		
		edgeList = new Edge[E];
		for(int i=0; i<E; ++i) {
			line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			int weight = Integer.parseInt(line[2]);
			edgeList[i] = new Edge(from, to, weight); // 간선 저장
		}
		
		parents = new int[V+1];
		for(int i=1; i<=V; ++i) {
			parents[i] = i; // 자기자신을 부모로 초기화
		}
		
		Arrays.sort(edgeList); // 간선을 가중치를 기준으로 오름차순
		
		int cnt = 0;
		int totalWeight = 0;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				totalWeight+=edge.weight;
				if(++cnt==V-1) { // 정점의 갯수보다 하나 작개 선택하면 모든 간선을 선택한 것이므로
					break;
				}
			}
		}
		System.out.println(totalWeight);
	}
	
	public static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a]=find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		parents[bRoot]=aRoot;
		return true;
	}
}