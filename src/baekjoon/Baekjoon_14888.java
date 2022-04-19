package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 연산자 끼워넣기
/*
 * 중복조합 문제이다.
 * 무난하게 브루트포스로 가능한 모든 연산자의 경우를 고려하여 최대,최소값을 구하면 된다.
 */
public class Baekjoon_14888 {

	static int N,min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
	static int[] A;
	static int[] op; // 연산자 순서
	static int[] opCnt = new int[4]; // 연산자 갯수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		op = new int[N-1];
		String[] line = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			A[i] = Integer.parseInt(line[i]);
		}
		line = br.readLine().split(" ");
		for(int i=0; i<4; ++i) {
			opCnt[i] = Integer.parseInt(line[i]);
		}
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int n) {
		if(n==N-1) { // N-1개의 연산자 다 선택했으면
			calcResult();
			return;
		}
		
		for(int i=0; i<4; ++i) {
			if(opCnt[i]>0) {
				opCnt[i]--;
				op[n]=i;
				dfs(n+1);
				opCnt[i]++;
			}
			
		}
	}
	
	public static void calcResult() {
		int result = A[0];
		for(int i=0; i<N-1; ++i) {
			int operator = op[i];
			if(operator==0) {
				result = result+A[i+1];
			}else if(operator==1) {
				result = result-A[i+1];
			}else if(operator==2) {
				result = result*A[i+1];
			}else {
				result = result/A[i+1];
			}
		}
		if(min>result) min = result;
		if(max<result) max = result;
	}
}