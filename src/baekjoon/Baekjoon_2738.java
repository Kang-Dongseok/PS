package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon_2738 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] a = new int[N][M];
		int[][] b = new int[N][M];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				a[i][j] = Integer.parseInt(str[j]);
			}
		}
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				b[i][j] = Integer.parseInt(str[j]);
			}
		}
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(a[i][j]+b[i][j]+" ");
			}
			System.out.println();
		}
	}
}