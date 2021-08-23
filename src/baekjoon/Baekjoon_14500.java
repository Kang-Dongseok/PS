package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 테트로미노
/*
 * 각 도형을 회전시켜 나올 수 있는 모든 모양을 완전탐색하여 푼다. 생각보다 단순(?)한 문제
 * 시간복잡도: O(NM), 배열을 전부 검색하지만 N,M<=500 이므로 시간복잡도 걱정은 크게 안해도 된다.
 */
// DFS를 이용해서 푸는 방법도 있다! 나중에 꼭 도전해볼 것! => 예외상황 고려해야함
public class Baekjoon_14500 {

	static int N,M,result;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		for(int i=0;i<N;i++) {
			str = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M-3; ++j) {
				func1(i,j); // ㅡ
			}
		}
		for(int i=0; i<N-3; ++i) {
			for(int j=0; j<M; ++j) {
				func2(i,j); // ㅣ
			}
		}
		for(int i=0; i<N-1; ++i) {
			for(int j=0; j<M-1; ++j) {
				func3(i,j); // ㅁ
			}
		}
		for(int i=0; i<N-2; ++i) { // 3X2 모양
			for(int j=0; j<M-1; ++j) {
				func4(i,j);
				func5(i,j);
				func6(i,j);
			}
		}
		for(int i=0; i<N-1; ++i) { // 2X3 모양
			for(int j=0; j<M-2; ++j) {
				func7(i,j);
				func8(i,j);
				func9(i,j);
			}
		}
		System.out.println(result);
	}
	// ㅁㅁㅁㅁ
	public static void func1(int r, int c) { // ㅡ 모양 합
		int sum=0;
		for(int i=c; i<c+4; ++i) {
			sum+=arr[r][i];
		}
		if(sum>result) result=sum;
	}
//	ㅁ
//	ㅁ
//	ㅁ
//	ㅁ
	public static void func2(int r, int c) { // ㅣ모양 합
		int sum=0;
		for(int i=r; i<r+4; ++i) {
			sum+=arr[i][c];
		}
		if(sum>result) result=sum;
	}
//	ㅁㅁ
//	ㅁㅁ
	public static void func3(int r, int c) { // ㅁ모양 합
		int sum =0;
		for(int i=r; i<r+2; ++i) {
			for(int j=c; j<c+2; ++j) {
				sum+=arr[i][j];
			}
		}
		if(sum>result) result=sum;
	}
//	ㅁ		ㅁ		ㅁ ㅁ
//	ㅁ		ㅁ ㅁ		ㅁ
//	ㅁ ㅁ		ㅁ		ㅁ
	public static void func4(int r, int c) { // 세로길이3 + 오른쪽 한칸
		int sum =0;
		for(int i=r; i<r+3; ++i) {
			sum+=arr[i][c];
		}
		int num1=arr[r][c+1];
		int num2=arr[r+1][c+1];
		int num3=arr[r+2][c+1];
		if(num1>num2) {
			if(num1>num3) sum += num1;
			else sum += num3;
		} else {
			if(num2>num3) sum += num2;
			else sum += num3;
		}
		if(sum>result) result=sum;
	}
//	    ㅁ		    ㅁ		ㅁ ㅁ
//	    ㅁ		ㅁ ㅁ		    ㅁ
//	ㅁ ㅁ		    ㅁ		    ㅁ
	public static void func5(int r, int c) { // 세로길이3 + 왼쪽 한칸(위에와 좌우대칭)
		int sum =0;
		for(int i=r; i<r+3; ++i) {
			sum+=arr[i][c+1];
		}
		int num1=arr[r][c];
		int num2=arr[r+1][c];
		int num3=arr[r+2][c];
		if(num1>num2) {
			if(num1>num3) sum += num1;
			else sum += num3;
		} else {
			if(num2>num3) sum += num2;
			else sum += num3;
		}
		if(sum>result) result=sum;
	}
//	ㅁ		    ㅁ
//	ㅁ ㅁ		ㅁ ㅁ
//	    ㅁ		ㅁ
	public static void func6(int r, int c) { 
		int sum =0;
		sum += arr[r][c]+arr[r+1][c]+arr[r+1][c+1]+arr[r+2][c+1];
		if(sum>result) result=sum;
		sum =0;
		sum += arr[r][c+1]+arr[r+1][c+1]+arr[r+1][c]+arr[r+2][c];
		if(sum>result) result=sum;
	}
//	ㅁ ㅁ ㅁ	ㅁ ㅁ ㅁ	ㅁ ㅁ ㅁ
//	ㅁ		    ㅁ		        ㅁ
	public static void func7(int r, int c) { // 가로길이3 + 아래쪽 한칸
		int sum =0;
		for(int i=c; i<c+3; ++i) {
			sum+=arr[r][i];
		}
		int num1=arr[r+1][c];
		int num2=arr[r+1][c+1];
		int num3=arr[r+1][c+2];
		if(num1>num2) {
			if(num1>num3) sum += num1;
			else sum += num3;
		} else {
			if(num2>num3) sum += num2;
			else sum += num3;
		}
		if(sum>result) result=sum;
	}
//	ㅁ		    ㅁ		        ㅁ
//	ㅁ ㅁ ㅁ	ㅁ ㅁ ㅁ	ㅁ ㅁ ㅁ
	public static void func8(int r, int c) { // 가로길이3 + 윗쪽 한칸
		int sum =0;
		for(int i=c; i<c+3; ++i) {
			sum+=arr[r+1][i];
		}
		int num1=arr[r][c];
		int num2=arr[r][c+1];
		int num3=arr[r][c+2];
		if(num1>num2) {
			if(num1>num3) sum += num1;
			else sum += num3;
		} else {
			if(num2>num3) sum += num2;
			else sum += num3;
		}
		if(sum>result) result=sum;
	}
//	ㅁ ㅁ		    ㅁ ㅁ
//	    ㅁ ㅁ	ㅁ ㅁ
	public static void func9(int r, int c) { 
		int sum =0;
		sum += arr[r][c+1]+arr[r][c+2]+arr[r+1][c]+arr[r+1][c+1];
		if(sum>result) result=sum;
		sum =0;
		sum += arr[r][c]+arr[r][c+1]+arr[r+1][c+1]+arr[r+1][c+2];
		if(sum>result) result=sum;
	}
}
