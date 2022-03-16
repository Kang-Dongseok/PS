package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// 파티
/*
 * 처음에 모든 정점에서의 최단거리를 생각하여 플로이드-와샬 알고리즘을 떠올렸지만,
 * N이 최대 1000이므로 플로이드-와샬의 O(N^3) 시간복잡도는 시간초과가 날 것 같아서
 * 다익스트라로 해결해보려고 생각하였다.
 * 결국 각 마을에서 X번 까지와 X번로부터 각 마을까지의 최단거리 두 번만 계산하면 되므로,
 * X마을을 기준으로 X번으로부터 각마을까지 다익스트라로 한번 계산을 한 후,
 * 모든 간선의 출발점과 끝점을 뒤집은 후 다시 X번 마을로부터 모든 간선까지의 거리를 계산을 하면 그 값이 모든 마을에서 X번 마을까지의 거리와 동일하다.
 * 
 * 시간 복잡도: O(ELogV)
 */
public class Baekjoon_1238 {
	
	static class Vertex implements Comparable<Vertex>{
		int no,distance;
		public Vertex(int no, int distance) {
			super();
			this.no = no;
			this.distance = distance;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.distance,o.distance);
		}
	}
	
	static int N,M,X;
	
	public static void main(String[] args) throws IOException {
		final int INFINITY = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		X = Integer.parseInt(line[2]);
		
		List<Vertex>[] adjListFromX = new LinkedList[N+1]; //    
		List<Vertex>[] adjListToX = new LinkedList[N+1];
		
		for(int i=1; i<N+1; ++i) {
			adjListFromX[i] = new LinkedList<Vertex>(); // 0번 인덱스는 사용x
			adjListToX[i] = new LinkedList<Vertex>(); // 0번 인덱스는 사용x
		}
		
		for(int i=0; i<M; ++i) {
			line = br.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			int cost = Integer.parseInt(line[2]);
			adjListFromX[start].add(new Vertex(end, cost));
			adjListToX[end].add(new Vertex(start, cost));
		}
		
		int[] distFromX = new int[N+1]; // X마을로부터 각각의 마을까지의 최단거리
		int[] distToX = new int[N+1]; // 각각의 마을로부터 X마을까지의 최단거리
		for(int i=1; i<N+1; ++i) {
			distFromX[i] = INFINITY; // 무한대로 값을 초기화
			distToX[i] = INFINITY;
		}
		
		distFromX[X] = 0; // 시작점의 거리는 0으로 초기화
		distToX[X] = 0; // 시작점의 거리는 0으로 초기화
		boolean[] visitedFromX = new boolean[N+1];
		boolean[] visitedToX = new boolean[N+1];
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		Vertex current = null;
		
		// distFromX 채우기
		pq.offer(new Vertex(X, 0)); // 시작점을 pq에 삽입
		while(!pq.isEmpty()) {
			current = pq.poll();
			if(visitedFromX[current.no]) continue; // 이미 방문한 곳이면 pass
			visitedFromX[current.no] = true;
			
			for(int i=0,size=adjListFromX[current.no].size(); i<size; ++i) {
				Vertex v = adjListFromX[current.no].get(i);
				if(!visitedFromX[v.no] && distFromX[v.no] > current.distance + v.distance) { // v를 아직 방문안했고, v까지 기존의 최적값보다 현재 선택된 노드에서 v까지 이동거리의 합이 더 작으면
					distFromX[v.no] = current.distance + v.distance; // 최솟값 갱신
					pq.offer(new Vertex(v.no, distFromX[v.no]));
				}
			}
		}
		// distToX 채우기
		pq.offer(new Vertex(X, 0)); // 시작점을 pq에 삽입
		while(!pq.isEmpty()) {
			current = pq.poll();
			if(visitedToX[current.no]) continue; // 이미 방문한 곳이면 pass
			visitedToX[current.no] = true;
			
			for(int i=0,size=adjListToX[current.no].size(); i<size; ++i) {
				Vertex v = adjListToX[current.no].get(i);
				if(!visitedToX[v.no] && distToX[v.no] > current.distance + v.distance) { // v를 아직 방문안했고, v까지 기존의 최적값보다 현재 선택된 노드에서 v까지 이동거리의 합이 더 작으면
					distToX[v.no] = current.distance + v.distance; // 최솟값 갱신
					pq.offer(new Vertex(v.no, distToX[v.no]));
				}
			}
		}
		
		int result = 0;
		for(int i=1; i<N+1; ++i) {
			if(result<distFromX[i]+distToX[i]) result=distFromX[i]+distToX[i]; // 최단거리들 중에 최댓값 갱신
		}
		System.out.println(result);
	}
}