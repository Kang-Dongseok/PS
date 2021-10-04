package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 키 순서
public class Baekjoon_2458 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int INF = 999999;
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] matrix = new int[N+1][N+1]; // 플로이드-와샬 알고리즘을 사용하기 위한 배열
		for(int i=0; i<N+1; ++i) {
			Arrays.fill(matrix[i], INF); // 무한대로 값을 초기화
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			matrix[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1; // 단반향 그래프 초기화
		}
		// 플로이드-와샬 알고리즘 사용
		for(int k=1; k<=N; ++k) { // 경유지
			for(int i=1; i<=N; ++i) { // 출발지
				for(int j=1; j<=N; ++j) { // 도착지
					if(matrix[i][j]>matrix[i][k]+matrix[k][j]) matrix[i][j]=matrix[i][k]+matrix[k][j];
				}
			}
		}
		int[] sum = new int[N+1];
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				if(matrix[i][j]!=INF) { // (i,j) 경로가 존재하면
					sum[i]++; // i보다 큰사람 한명 추가
					sum[j]++; // j보다 작은사람 한명 추가
				}
			}
		}
		int result = 0;
		for(int i=1; i<=N; ++i) {
			if(sum[i]==N-1) result++; // 자신보다 키큰사람과 키작은 사람의 총합이 N-1이면 정답
		}
		System.out.println(result);
	}
}