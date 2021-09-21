package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 바이러스
public class Baekjoon_2606 {

	static boolean[] visited;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine()); // 컴퓨터의 갯수
		int E = Integer.parseInt(br.readLine()); // 간선의 갯수
		list = new ArrayList[V+1];
		visited = new boolean[V+1];
		for(int i=1; i<V+1; ++i) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<E; ++i) {
			String[] str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);
			list[from].add(to);
			list[to].add(from);
		}
		dfs(1);
		int result = 0;
		for(int i=2; i<V+1; ++i) { // 1번 컴퓨터 제외하고 이므로 2번부터 시작
			if(visited[i]) result++;
		}
		System.out.println(result);
	}
	private static void dfs(int n) {
		visited[n]=true;
		for(int vertex : list[n]) {
			if(!visited[vertex]) dfs(vertex);
		}
	}
}