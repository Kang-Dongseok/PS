package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 부녀회장이 될테야
public class Baekjoon_2775 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] map = new int[15][15];
		for(int i=1; i<15; ++i) {
			map[0][i] = i;
		}
		for(int i=1; i<15; ++i) {
			map[i][1] = 1;
		}
		for(int i=1; i<15; ++i) {
			for(int j=2; j<15; ++j) {
				map[i][j] = map[i-1][j]+map[i][j-1];
				
			}
		}
		for(int tc=0; tc<T; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			System.out.println(map[k][n]);
		}
	}
}