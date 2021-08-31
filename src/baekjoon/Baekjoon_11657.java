package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 타임머신
/*
 * 양의 정수가 아닌 0이나 음수 가중치의 간선이 존재하므로
 * 벨만 포드 알고리즘을 이용하여 해결한다.
 * 시간복잡도 : O(VE), 벨만 포드 알고리즘의 시간복잡도는 O(VE) 이다.
 */
public class Baekjoon_11657 {

	static class Edge { // 간선 정보
		int from,to,weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	static Edge[] edgeList; // 간선들의 정보
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int INFINITY = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		edgeList = new Edge[M];
		int start = 0; // 시작점 1번 도시, 인덱스는 0
		long[] distance = new long[N]; // 시작점으로부터 최단거리를 계산하는 배열
		
		// 간선들의 정보 저장
		for(int i=0; i<M; i++) {
			line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0])-1; // 출발지 번호
			int to = Integer.parseInt(line[1])-1; // 도착지 번호
			int weight = Integer.parseInt(line[2]);
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.fill(distance, INFINITY); // 거리배열 무한대로 초기화
		distance[start] = 0; // 시작점은 시작점으로부터 거리가 0 이므로 초기화
		
		// N-1번 만큼 돌면서 거리를 갱신하고, 마지막 한 번은 돌면서 무한루프가 있는지 확인하는 작업으로 총 N번 반복
		for(int i=0; i<N; ++i) {
			// 한 번 반복마다 첨부터 끝까지 모든 간선을 돌면서 시작점으로부터의 거리를 갱신한다.
			for(Edge edge : edgeList) {
				if(distance[edge.from]!=INFINITY && distance[edge.to] > distance[edge.from]+edge.weight) { // 간선이 존재하고 거리가 짧으면
					distance[edge.to] = distance[edge.from]+edge.weight;
					if(i==N-1) { // 마지막 N번쨰에 거리가 더 짧은 간선이 생기면 무한루프가 존재한다는 의미이므로
						System.out.println(-1);
						return;
					}
				}
			}
		}
		// 무한루프가 존재하지 않는다면
		for(int i=1; i<N; i++) {
			long num = distance[i];
			if(num!=INFINITY) sb.append(num).append("\n");
			else sb.append(-1).append("\n");
		}
		System.out.print(sb.toString());
	}
}