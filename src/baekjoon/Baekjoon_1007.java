package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 벡터 매칭
public class Baekjoon_1007 {

	static int N;
	static double answer;
	static int[] posSum;
	static ArrayList<int[]> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; ++t) {
			list = new ArrayList<int[]>();
			N = Integer.parseInt(br.readLine());
			posSum = new int[2];
			answer = Double.MAX_VALUE;
			for(int i=0; i<N; ++i) {
				String[] str = br.readLine().split(" ");
				int r = Integer.parseInt(str[0]);
				int c = Integer.parseInt(str[1]);
				list.add(new int[] {r,c});
				posSum[0] += r;
				posSum[1] += c;
			}
			dfs(0,0,new int[] {0,0});
			System.out.println(answer);
		}
	}
	
	public static void dfs(int n, int idx, int[] sum) {
		if(idx-n>N/2) return; // 가지치기
		if(n==N/2) {
			long r = Math.abs(2*sum[0]-posSum[0]);
			long c = Math.abs(2*sum[1]-posSum[1]);
			answer = Math.min(answer, Math.sqrt(r*r+c*c));
			return;
		}
		for(int i=idx; i<N; ++i) {
			int[] cur = list.get(i);
			int[] nextSum = new int[] {sum[0]+cur[0],sum[1]+cur[1]};
			dfs(n+1,i+1,nextSum);
		}
	}
}