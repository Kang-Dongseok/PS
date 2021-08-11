package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 배열 돌리기4
public class Baekjoon_17406 {

	static int N,M,K;
	static int[][] arr;
	static boolean[] isSelected;
	static int[][] arrayRCS;
	static int result = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		isSelected = new boolean[K];
		arr = new int[N][M];
		// 배열 생성
		for(int i=0; i<N; i++) {
			String[] str2 = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(str2[j]);
			}
		}
		// RCS 값을 저장하는 배열 생성
		arrayRCS = new int[K][3];
		for(int i=0; i<K; i++) {
			String[] rcs = br.readLine().split(" ");
			arrayRCS[i][0] = Integer.parseInt(rcs[0]);
			arrayRCS[i][1] = Integer.parseInt(rcs[1]);
			arrayRCS[i][2] = Integer.parseInt(rcs[2]);
		}
		
		func(arr,0);
		System.out.println(result);
	}

	public static int[][] myArrCopy(int[][] arr){ // 2차원 배열 깊은 복사
		int[][] copiedArr = new int[arr.length][];
		for(int i=0;i<arr.length;i++) {
			copiedArr[i] = arr[i].clone();
		}
		return copiedArr;
	}
	public static void func(int[][] arr, int n) { // 재귀를 통해 순열 시작
		if(n==K) { // K번 회전 다 시켰으면
			if(result > sum(arr)) result = sum(arr); // 배열 최솟값 갱신
			return;
		}
		for(int i=0;i<K;i++) {
			if(isSelected[i]==false) {
				isSelected[i] = true;
				int[][] copiedArr = myArrCopy(arr);
				rotation(copiedArr, arrayRCS[i][0], arrayRCS[i][1], arrayRCS[i][2]);
				func(copiedArr,n+1);
				isSelected[i] = false;
			}
		}
		
	}
	public static int sum(int[][] arr) { // 배열의 행의 합의 최솟값 반환
		int min = Integer.MAX_VALUE;
		for(int[] ar : arr) {
			int sum = 0;
			for(int a : ar) {
				sum += a;
			}
			if(sum < min) min = sum;
		}
		return min;
	}
	public static void rotation(int[][] arr, int r, int c, int s) { // 사각형 회전
		for(int i=0; i<s; i++) {
			lineRotation(arr,r,c,s,i);
		}
	}
	public static void lineRotation(int[][] arr, int r, int c, int s, int line) { // 바깥부터 line번째 줄 회전, 제일 바깥 0번째 줄
		int cStart = c - s - 1 + line; // 가로 시작 인덱스
		int cEnd = c + s - 1 - line; // 가로 끝 인덱스
		int rStart = r - s - 1 + line; // 세로 시작 인덱스
		int rEnd = r + s - 1 - line; // 세로 끝 인덱스
		int temp = arr[rStart][cStart]; // 왼쪽 젤 위 원소 임시 저장
		
		for(int i=rStart+1;i<=rEnd;i++) { // 왼쪽줄 위로 한칸 이동, 왼쪽 젤 위 시작점은 어차피 temp로 덮어씌울거기 떄문에 움직일 필요 없으므로 line+1까지만 이동
			arr[i-1][cStart] = arr[i][cStart];
		}
		for(int i=cStart+1;i<=cEnd;i++) { // 아래줄  좌로 한칸 이동
			arr[rEnd][i-1] = arr[rEnd][i];
		}
		for(int i=rEnd-1;i>=rStart;i--) { // 오른쪽줄 아래로 한칸 이동
			arr[i+1][cEnd] = arr[i][cEnd];
		}
		for(int i=cEnd-1;i>=cStart+1;i--) { // 윗줄 우로 한칸 이동
			arr[rStart][i+1] = arr[rStart][i];
		}
		arr[rStart][cStart+1] = temp; // 덮어씌우기
	}
	
}
