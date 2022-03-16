package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 선발 명단
public class Baekjoon_3980 {

	static int result;
	static boolean[] used;
	static int[][] info;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		info = new int[11][11]; // 선수들의 능력치
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; ++t) {
			result = 0;
			used = new boolean[11];
			for(int i=0; i<11; ++i) {
				line = br.readLine().split(" ");
				for(int j=0; j<11; ++j) {
					info[i][j] = Integer.parseInt(line[j]);
				}
			}
			permutation(0,0);
			System.out.println(result);
		}
	}
	public static void permutation(int n, int sum) {
		if(n==11) {
			if(result<sum) result=sum;
			return;
		}
		
		for(int i=0; i<11; ++i) {
			if(!used[i] && info[n][i]!=0) {
				used[i]=true;
				permutation(n+1,sum+info[n][i]);
				used[i]=false;
			}
		}
		
	}
}