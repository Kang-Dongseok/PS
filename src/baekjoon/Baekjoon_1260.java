package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// DFS와 BFS
public class Baekjoon_1260 {
	
	static class Node{
		int vertext;
		Node link;
		public Node(int vertext, Node link) {
			super();
			this.vertext = vertext;
			this.link = link;
		}
		@Override
		public String toString() {
			return vertext + " ";
		}
		
	}
	static int N,M,V;
	static Node[] adjList;
	static StringBuilder sb_bfs = new StringBuilder();
	static StringBuilder sb_dfs = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		V = Integer.parseInt(line[2]);
		adjList = new Node[N+1]; // 노드1번부터 시작, null값으로 자동 초기화
		// 인접리스트를 오름차순으로 생성한다.
		for(int i=0; i<M; i++) {
			line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			// from에서 to간선 집어넣기
			if(adjList[from]==null || adjList[from].vertext>to) adjList[from] = new Node(to, adjList[from]); // 널이면 젤 앞쪽
			else { // 널 아니면 오름차순 위치 찾아서 삽입
				Node tmp = adjList[from];
				while(tmp.vertext<to) {
					if(tmp.link!=null && tmp.link.vertext<to) tmp=tmp.link;
					else break;
				}
				tmp.link = new Node(to, tmp.link);
			}
			// to에서 from간선 집어넣기
			if(adjList[to]==null || adjList[to].vertext>from) adjList[to] = new Node(from, adjList[to]); // 널이면 젤 앞쪽
			else { // 널 아니면 오름차순 위치 찾아서 삽입
				Node tmp = adjList[to];
				while(tmp.vertext<from) {
					if(tmp.link!=null && tmp.link.vertext<from) tmp=tmp.link;
					else break;
				}
				tmp.link = new Node(from, tmp.link);
			}
		}
		
		boolean[] visited = new boolean[N+1];
		dfs(V,visited);
		System.out.println(sb_dfs);
		bfs(V);
		System.out.println(sb_bfs);
	}
	
	private static void bfs(int start) {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N+1];
		
		queue.offer(start);
		visited[start] = true;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb_bfs.append(current).append(" ");
			for(Node temp = adjList[current]; temp != null; temp = temp.link) {
				if(!visited[temp.vertext]) {
					queue.offer(temp.vertext);
					visited[temp.vertext]=true;
				}
			}
		}
	}
	private static void dfs(int start, boolean[] visited) {
		
		visited[start] = true;
		sb_dfs.append(start).append(" ");
		
		for(Node temp = adjList[start]; temp != null; temp = temp.link) {
			if(!visited[temp.vertext]) {
				dfs(temp.vertext, visited);
			}
		}
	}
}
