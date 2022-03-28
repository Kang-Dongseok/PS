package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 구간 합 구하기 5
public class Baekjoon_11660 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N+1][N+1];
		for(int i=1; i<=N; ++i) {
			str = br.readLine().split(" ");
			for(int j=1; j<=N; ++j) {
				map[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		for(int i=1; i<=N; ++i) {
			for(int j=2; j<=N; ++j) {
				map[i][j] += map[i][j-1];
			}
		}
		for(int i=2; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				map[i][j] += map[i-1][j];
			}
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int x1 = Integer.parseInt(str[0]);
			int y1 = Integer.parseInt(str[1]);
			int x2 = Integer.parseInt(str[2]);
			int y2 = Integer.parseInt(str[3]);
			sb.append(map[x2][y2]-map[x1-1][y2]-map[x2][y1-1]+map[x1-1][y1-1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}