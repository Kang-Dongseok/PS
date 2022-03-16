package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 사회망 서비스(SNS)
/*
 * 본인이 얼리 어답터일때와 아닐때 두 경우를 나누어서 생각하면 된다.
 * 본인이 맞을때는 자식은 맞을수도 있고 아닐수도 있는 모든 경우 중에 최솟값을 구하면 되고,
 * 본인이 아닐때는 자식이 모두 맞는 경우만 생각하면 된다.
 * dp일 것 같다는 생각은 들었지만, 트리 구조에서 dp를 적용시키는게 익숙하지 않았다.
 * 결국 구글링하여 다른 풀이를 참고하였다.
 * 아직 dp의 길은 멀고도 험하구나...
 */
public class Baekjoon_2533 {
	
	static boolean[] visited;
	static ArrayList<Integer>[] adjList; // 인접리스트
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 색종이 수
		visited = new boolean[N+1];
		dp = new int[N+1][2];
		adjList = new ArrayList[N+1];
		for(int i=1; i<N+1; ++i) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		String[] line;
		for(int i=1; i<N; ++i) {
			line = br.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			adjList[start].add(end);
			adjList[end].add(start);
		}
		
		dfs(1); // 1번 노드를 기준으로 dfs시작
		System.out.println(Math.min(dp[1][0], dp[1][1])); // 1번노드가 얼리 어답터가 아닐때와 맞을때 중 최솟값을 선택
	}

	public static void dfs(int num) {
		visited[num] = true; // 방문 표시
		dp[num][0] = 0; // 우선 자신이 얼리 어답터가 아니면 지금까지의 인원수가 0
		dp[num][1] = 1; // 우선 자신이 얼리 어답터이면 지금까지의 인원수가 1
		
		for(int child : adjList[num]) {
			if(!visited[child]) { // 자식 노드를 아직 방문하지 않았으면
				dfs(child);
				dp[num][0] += dp[child][1]; // 현재 노드가 얼리 어답터가 아니면 자식은 모두 얼리 어답터이어야 한다.
				dp[num][1] += Math.min(dp[child][0], dp[child][1]); // 현재 노드가 얼리 어답터이면 자식은 어떤 경우든 적은 경우를 택하면 된다.
			}
		}
	}
}