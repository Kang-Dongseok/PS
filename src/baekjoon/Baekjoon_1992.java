package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 쿼드트리
public class Baekjoon_1992 {

	static int N;
	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for(int i=0; i<N; ++i) { // 초기 배열 저장
			arr[i] = br.readLine().toCharArray();
		}
		func(0, 0, N);
		System.out.println(sb);
		
	}
	public static void func(int r, int c, int len) {
		char ch = arr[r][c];
		for(int i=r; i<r+len; ++i) { // (r,c)부터 길이 len만큼의 정사각형 탐색
			for(int j=c; j<c+len; ++j) {
				if(arr[i][j]!=ch) { // 사각형 내부가 전부 같은 값이 아니면
					sb.append('('); // 괄호 열기
					func(r,c,len/2); // 왼쪽 위
					func(r,c+len/2,len/2); // 오른쪽 위
					func(r+len/2,c,len/2); // 왼쪽 아래
					func(r+len/2,c+len/2,len/2); // 왼쪽 아래
					sb.append(')'); // 괄호 닫기
					return;
				}
			}
		}
		// 사각형 내부가 전부 같은 값이면
		if(ch=='0') sb.append('0');
		else sb.append('1');
	}
	
}