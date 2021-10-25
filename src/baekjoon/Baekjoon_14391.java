package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 종이 조각
/*
 * 1. 각각의 칸을 가로로 자를지, 세로로 자를지 구분한다.(isVertical 배열을 이용해서 구분)
 * 2. 부분집합의 갯수처럼 2^16의 경우의 수 만큼 모양에 맞게 계산을 하여 총 합을 구한다.
 * 시간복잡도 : 최대 4*4=16이므로 2^16=65536으로 시간이 충분하다 생각해서 브루트포스로 풀었다.
 * 완탐일거라고 생각했지만, 가로세로를 어떤식으로 구분지어 할 것인지 빨리 떠오르지 않아서 생각보다 어려웠다.
 */
public class Baekjoon_14391 {

	static int N,M,result;
	static int[][] map;
	static boolean[][] isVertical; // true인 곳은 세로로 종이를 자른다
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split("");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		isVertical = new boolean[N][M];
		cut(0);
		System.out.println(result);
		
	}
	public static void cut(int idx) { // 해당 idx칸의 map을 가로,세로로 구분지어 자르기
		if(idx==N*M) {
			calcSum();
			return;
		}
		// 1차원 인덱스를 2차원으로 변환
		int r = idx/M;
		int c = idx%M;
		isVertical[r][c] = true; // r,c 세로
		cut(idx+1);
		
		isVertical[r][c] = false; // r,c 가로
		cut(idx+1);
		
	}
	public static void calcSum() { // 합을 계산하는 메소드
		int sum=0,num=0;
		
		// 세로 합 계산
		for(int c=0; c<M; c++) {
			num=0;
			for(int r=0; r<N; r++) {
				if(isVertical[r][c]) { // 세로인 곳을 발견하면
					num *= 10; // 기존의 수 한자리 증가
					num += map[r][c]; // 1의 자리수 더하기
				}else { // 중간에 세로인 숫자가 끊기면
					sum += num; 
					num = 0;
				}
			}
			sum += num; // 한 열이 끝나면 마지막 세로 숫자를 더함
		}
		
		// 가로 합 계산
		for(int r=0; r<N; r++) {
			num=0;
			for(int c=0; c<M; c++) {
				if(!isVertical[r][c]) { // 가로인 곳을 발견하면(세로가 아닌 곳)
					num *= 10; // 기존의 수 한자리 증가
					num += map[r][c]; // 1의 자리수 더하기
				}else { // 중간에 가로인 숫자가 끊기면
					sum += num; // 
					num = 0;
				}
			}
			sum += num; // 한 행이 끝나면 마지막 가로 숫자를 더함
		}
		result = Math.max(result, sum);
	}
}