package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 구슬 찾기
public class Baekjoon_2617 {

	static boolean[] visited;
	static int[][] total = new int[100][2]; // 작은수와 큰수 갯수 저장
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		list = new ArrayList[100];
		for(int i=0; i<100; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int bigger = Integer.parseInt(str[0])-1;
			int smaller = Integer.parseInt(str[1])-1;
			list[bigger].add(smaller);
		}
		int answer = 0;
		for(int i=0; i<N; ++i) {
			visited = new boolean[100];
			dfs(i, i); // 모든 숫자마다 자기부터 시작해서 작은 수 검색
		}
		for(int i=0; i<N; ++i) {
			if(total[i][0]>N/2 || total[i][1]>N/2) answer++;
		}
		System.out.println(answer);
	}
	
	public static void dfs(int start, int cur) {
		visited[cur] = true; // 방문표시
		
		for(int next : list[cur]) {
			if(!visited[next]) { // 아직 방문 안했으면
				total[start][0]++; // start보다 작은 갯수 1 증가
				total[next][1]++; // next보다 큰 갯수 1 증가
				dfs(start, next);
			}
		}
	}
}