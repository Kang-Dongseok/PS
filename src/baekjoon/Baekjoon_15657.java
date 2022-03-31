package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// N과 M(8)
public class Baekjoon_15657 {
	
	static int N,M;
	static int[] arr;
	static int[] answer;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N];
		answer = new int[M];
		str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		dfs(0,0);
		System.out.println(sb.toString());
	}
	
	public static void dfs(int num, int idx) {
		if(num==M) {
			func();
			return;
		}
		
		for(int i=idx; i<N; ++i) {
			answer[num] = arr[i];
			dfs(num+1,i);
		}
	}
	
	public static void func() {
		for(int i=0; i<M; ++i) {
			sb.append(answer[i]).append(" ");
		}
		sb.append("\n");
	}
}