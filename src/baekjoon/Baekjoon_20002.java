package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사과나무
public class Baekjoon_20002 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		for(int i=0; i<N; ++i) { // 가로로 누적합
			for(int j=1; j<N; ++j) {
				map[i][j] += map[i][j-1];
			}
		}
		for(int i=1; i<N; ++i) { // 세로로 누적합
			for(int j=0; j<N; ++j) {
				map[i][j] += map[i-1][j];
			}
		}
		int max = -1000; // 한 칸의 최솟값이 -1000이므로
		for(int k=0; k<N; ++k) {
			for(int i=0; i+k<N; ++i) {
				for(int j=0; j+k<N; ++j) {
					int sum = calcSum(i,j,k,map); // 
					if(max<sum) max = sum; // 최댓값 갱신
				}
			}
		}
		System.out.println(max);
	}
	public static int calcSum(int i, int j, int k, int[][] map) {
		int total = map[i+k][j+k];
		if(i>0) total -= map[i-1][j+k];
		if(j>0) total -= map[i+k][j-1];
		if(i>0 && j>0) total += map[i-1][j-1];
		return total;
	}
}
