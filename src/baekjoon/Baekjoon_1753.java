package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최단경로
/*
 * 다익스트라 알고리즘을 이용해 해결.
 * 시간복잡도 : O((E+V)logV)
 */
public class Baekjoon_1753 {
	
	static class Node{
		int vertex; // 인접정점 인덱스
		int weight; // 인접정점 가중치
		Node link;
		public Node(int vertex, int weight, Node link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}
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
	
	static int V,E; // 정점, 간선 개수
	static Node[] adjList; // 인접리스트 (가중치 없음)
	static int[] distance;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		adjList = new Node[V+1]; // 인접리스트 생성
		distance = new int[V+1]; // start에서 자신으로 오는 최소비용(다른 정점을 여러개 거칠수도 아닐수도 있음)
		visited = new boolean[V+1]; // 경유지로 고려해본 정점인지 관리
		int start = Integer.parseInt(br.readLine()); // 시작 정점의 번호
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to,weight,adjList[from]); // 기존에 간선이 없으면 인접리스트 제일 앞쪽에 추가
		}
		
		for(int i=1; i<V+1; i++) {
			distance[i] = Integer.MAX_VALUE; // MAX값으로 초기화
		}
		distance[start] = 0; // 시작점에서 시작점으로의 거리는 0으로 초기화
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(start, 0));
		
		Vertex current = null;
		
		while(!pq.isEmpty()) {
			// 아직 경유지로 고려되지 않은 정점 중에 출발지에서 가장 가까운 정점을 선택

			current = pq.poll();
			if(visited[current.no]) continue;
			
			visited[current.no] = true; // 선택한 정점을 방문 처리
			
			// 현재 선택된 정점을 경유지로해서 아직 처리하지 않은 나머지 정점들과의 거리를 계산해서 갱신
			for(Node node = adjList[current.no]; node != null; node = node.link) {
				if(!visited[node.vertex] && distance[node.vertex] > node.weight + distance[current.no]) {
					distance[node.vertex] = distance[current.no] + node.weight;
					pq.offer(new Vertex(node.vertex, distance[node.vertex]));
				}
			}
		}
		for(int i=1; i<V+1; i++) {
			if(distance[i]==Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}
}