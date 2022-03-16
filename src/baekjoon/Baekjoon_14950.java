package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 정복자
/*
 * prim 알고리즘을 연습해볼겸 적용하여 해결하려고 하였으나
 * 처음에 인접행렬을 사용하여 메모리초과...
 * 그 다음에 인접리스트를 이용하여 해결하였다.
 * prim 알고리즘의 시간복잡도: O(V^2)
 * kruskal 알고리즘의 시간복잡도: O(ElogE)
 */
public class Baekjoon_14950 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int t = Integer.parseInt(str[2]);
		
		boolean[] visited = new boolean[N+1];
		
		ArrayList<int[]>[] adjList = new ArrayList[N+1]; // {도착점, 가중치}
		for(int i=1; i<N+1; ++i) {
			adjList[i] = new ArrayList<int[]>();
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);
			int weight = Integer.parseInt(str[2]);
			adjList[from].add(new int[] {to,weight});
			adjList[to].add(new int[] {from,weight});
		}
		
		int[] minWeight = new int[N+1];
		for(int i=1; i<=N; ++i) {
			minWeight[i] = Integer.MAX_VALUE;
		}
		
		int totalWeight = 0;
		minWeight[1] = 0; // 1번 정점을 시작으로 임의로 지정
		
		for(int i=0; i<N; ++i) {
			int min = Integer.MAX_VALUE; // 가장 가까운 비용
			int minVertex = -1; // 가강 가까운 정점
			for(int j=1; j<=N; ++j) {
				if(!visited[j] && min>minWeight[j]) {
					min = minWeight[j];
					minVertex=j;
				}
			}
			visited[minVertex]=true; // 방문체크
			totalWeight+=min; // 가중치의 합 계산
			for(int[] arr : adjList[minVertex]) {
				int nextV = arr[0]; // 새로운 정점
				int nextW = arr[1]; // 새로운 가중치
				if(!visited[nextV] && nextW < minWeight[nextV]) {
					minWeight[nextV] = nextW;
				}
			}
		}
		totalWeight+=(N-2)*(N-1)/2*t;
		System.out.println(totalWeight);
	}
}