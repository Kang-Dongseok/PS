package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 도포로장
/*
 * 인접행렬로 만들었다가 메모리 초과, 인접리스트로 구현
 * 일반적인 다익스트라는 distance[i] 배열을 사용하지만
 * 이 문제는 k번 포장하는 경우를 메모이제이션을 사용해야 하므로
 * 2차원 배열을 사용하여 distance[i][k] ==> i번째 정점까지 k번 도로를 포장한 최단거리
 * 풀이법을 보고 이해하며 따라 풀었지만 여전히 생각보다 어렵다.
 * 나중에 꼭 다시 풀어볼 문제
 */
public class Baekjoon_1162 {

	static class Edge implements Comparable<Edge> {
		int no,cnt;
		long distance;
		public Edge(int no, int cnt, long distance) {
			super();
			this.no = no;
			this.cnt = cnt;
			this.distance = distance;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.distance, o.distance);
		}
	}
	static int N,M,K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		List<Edge>[] adjList = new ArrayList[N+1];
		long[][] distance = new long[N+1][K+1];
		
		for(int i=1; i<=N; ++i) {
			adjList[i] = new ArrayList<Edge>(); // 인접리스트 생성
			Arrays.fill(distance[i],Long.MAX_VALUE); // 거리값 초기화
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);
			int weight = Integer.parseInt(str[2]);
			adjList[from].add(new Edge(to, 0, weight));
			adjList[to].add(new Edge(from, 0, weight));
		}
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		distance[1][0] = 0; // 시작점은 거리가 0으로 초기화
		pq.offer(new Edge(1, 0, 0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int target = edge.no;
			int cnt = edge.cnt;
			long dist = edge.distance;
			
			if(distance[target][cnt]<dist) continue; // 기존의 최단거리보다 더 크면 비교할 필요가 없으므로 continue;
			
			for(Edge e : adjList[target]) {
				int nextTarget = e.no;
				long nextDist = dist + e.distance;
				
				// 도로 포장 안하는 경우
				if(distance[nextTarget][cnt]>nextDist) {
					distance[nextTarget][cnt]=nextDist;
					pq.offer(new Edge(nextTarget,cnt,nextDist));
				}
				
				// 도로 포장 하는 경우
				if(cnt<K && distance[nextTarget][cnt+1]>dist) {
					distance[nextTarget][cnt+1]=dist;
					pq.offer(new Edge(nextTarget,cnt+1,dist));
				}
				
			}
		}
		long min = Long.MAX_VALUE;
		for(int i=0; i<=K; ++i) {
			min = Math.min(min, distance[N][i]);
		}
		System.out.println(min);
	}
}