package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 트리의 지름
public class Baekjoon_1967 {

	static int node,maxLen;
	static boolean[] visited;
	static ArrayList<int[]>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n==1) {
			System.out.println(0);
			return;
		}
		adjList = new ArrayList[n+1];		
		visited = new boolean[n+1];
		for(int i=1; i<=n; ++i) {
			adjList[i] = new ArrayList<int[]>();
		}
		for(int i=1; i<n; ++i) {
			String[] str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);
			int cost = Integer.parseInt(str[2]);
			adjList[from].add(new int[] {to,cost});
			adjList[to].add(new int[] {from,cost});
		}
		
		dfs(1,0);
		visited = new boolean[n+1];
		dfs(node,0);
		System.out.println(maxLen);
	}

	public static void dfs(int n, int sum) {
		visited[n]=true;
		
		if(maxLen<sum) {
			maxLen=sum;
			node = n;
		}
		
		for(int[] node : adjList[n]) {
			int next = node[0];
			int cost = node[1];
			if(!visited[next]) {
				dfs(next,sum+cost);
			}
		}
	}
}