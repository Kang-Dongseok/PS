package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 이분 그래프
/*
 * 우선 그룹1과 그룹2로 나눈다.
 * 한 정점을 기준으로 dfs로 완전탐색을 하며 시작정점을 1번그룹에 배치하고,
 * 인접한 정점들은 2로 배치하며, 또 거기에 인접한 정점들은 1번 그룹으로 배치한다.
 * 만약 이미 배치가 된 정점과 인접하여 있다면 서로 다른 그룹이면 그냥 넘어가고,
 * 같은 그룹이면 탈락!
 */
public class Baekjoon_1707 {

	static boolean flag;
	static ArrayList<Integer>[] adjList;
	static int[] group;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			String[] str = br.readLine().split(" ");
			int V = Integer.parseInt(str[0]);
			int E = Integer.parseInt(str[1]);
			adjList = new ArrayList[V+1];
			group = new int[V+1];
			for(int i=1; i<=V; ++i) {
				adjList[i] = new ArrayList<Integer>();
			}
			for(int i=0; i<E; ++i) {
				str = br.readLine().split(" ");
				int from = Integer.parseInt(str[0]);
				int to = Integer.parseInt(str[1]);
				adjList[from].add(to);
				adjList[to].add(from);
			}
			flag = true;
			for(int i=1; i<=V; ++i) {
				if(group[i]==0) dfs(i,2); // 아직 그룹이 없는 정점을 찾아서 1번 그룹으로 시작하기
			}
			if(flag) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void dfs(int n, int before) { // n은 목표 정점의 번호, before은 이전 정점의 그룹번호
		if(group[n]==0) { // n번 정점이 아직 그룹이 없으면
			group[n] = 3-before; // 1과2중 before과 반대 그룹으로 배치
			int size = adjList[n].size();
			for(int i=0; i<size; ++i) {
				dfs(adjList[n].get(i), 3-before);
			}
			return;
		}
		// 다음 정점이 이미 그룹이 있으면
		if(group[n]==before) {
			flag = false; // 이전 정점과 같은 그룹이면 false
		}
	}
}
