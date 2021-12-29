package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 해킹
/*
 * 전형적인 다익스트라 알고리즘을 이용하는 문제이다.
 * 처음에 인접행렬을 이용하여 풀었더니 메모리가 초과되었다. (정점은 많지만 간선의 갯수가 적을 때 불리)
 * 그래서 인접리스트를 이용하여 해결하였다.
 * 오랜만에 다익스트라를 접해서 그런지 많이 까먹었다. 다시 연습할 필요가 있는 것 같다.
 * 시간복잡도: O(N): NlonN (PQ의 힙정렬)
 */
public class Baekjoon_10282 {

	static class Vertex implements Comparable<Vertex> {
		int no, distance;
		
		public Vertex(int no, int distance) {
			super();
			this.no = no;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int INFINITY = Integer.MAX_VALUE;
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; ++tc) {
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int d = Integer.parseInt(line[1]);
			int c = Integer.parseInt(line[2])-1; // 인덱스는 -1
			List<Vertex>[] adjList = new ArrayList[n]; // 인접리스트
			for(int i=0; i<n; ++i) {
				adjList[i] = new ArrayList<Vertex>();
			}
			int[] distance = new int[n];
			boolean[] visited = new boolean[n];
			
			for(int i=0; i<d; ++i) {
				line = br.readLine().split(" ");
				int a = Integer.parseInt(line[0])-1; // 인덱스는 -1
				int b = Integer.parseInt(line[1])-1; // 인덱스는 -1
				int s = Integer.parseInt(line[2]);
				adjList[b].add(new Vertex(a, s)); // b정점에서 a정점으로 비용s
			}
			Arrays.fill(distance, INFINITY); // 모든 정점까지의 비용을 무한대로 초기화
			distance[c] = 0; // c를 기준으로 자기자신의 거리비용은 0으로 초기화
			
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
			pq.offer(new Vertex(c, distance[c]));

			Vertex current = null;

			while (!pq.isEmpty()) {
				current = pq.poll(); // 방문하지 않은 정점들 중 최소가중치의 정점 선택
				if (visited[current.no]) continue; // 이미 방문한 정점이면 pass

				visited[current.no] = true; // 선택 정점 방문 처리

				for (int i = 0; i < adjList[current.no].size(); ++i) { // current정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
					Vertex nextV = adjList[current.no].get(i); // 목적 정점
					if (!visited[nextV.no] && distance[nextV.no] > current.distance + nextV.distance) { // 기존의 최적화된 값보다 현재정점을 경유해서 목표 정점에 닿는 거리가 짧으면
						distance[nextV.no] = current.distance + nextV.distance; // 값 최적화
						pq.offer(new Vertex(nextV.no, distance[nextV.no])); // 큐에 넣기
					}
				}
			}
			int resultNum=0,resultTime=0;
			for(int i=0,len=distance.length; i<len; ++i) {
				if(distance[i]<INFINITY) { // 해당 컴퓨터로 경로가 있으면
					resultNum++; // 감염된 갯수 증가
					if(resultTime<distance[i]) resultTime=distance[i]; // 해당 컴퓨터까지 도달하는 시간의 최대시간
				}
			}
			System.out.println(resultNum+" "+resultTime);
		}
	}
}