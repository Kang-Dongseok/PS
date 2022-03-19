package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 결혼식
public class Baekjoon_5567 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[n+1];
		ArrayList<Integer>[] adjList = new ArrayList[n+1];
		for(int i=1; i<n+1; ++i) {
			adjList[i] = new ArrayList<Integer>();
		}
		String[] line;
		for(int i=0; i<m; ++i) {
			line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			adjList[from].add(to);
			adjList[to].add(from);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		int answer = 0;
		visited[1] = true;
		for(int num : adjList[1]) {
			q.offer(num);
			visited[num] = true;
			answer++;
		}
		for(int cur : q) {
			for(int next : adjList[cur]) {
				if(!visited[next]) {
					answer++;
					visited[next] = true;
				}
			}
		}
		System.out.println(answer);
	}
}