package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 비밀 이메일
public class Baekjoon_2999 {

	static int N,R,C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 태그를 그대로 append, 최종 출력할 문자열
		String str = br.readLine();
		N = str.length();
		for(int i=1; i*i<=N; i++) {
			if(N%i==0) R=i; // 나누어 떨어지면 R 갱신
		}
		C = N/R;
		char[][] arr = new char[R][C]; // 행과 열을 뒤집어서 생성
		int idx = 0;
		for(int i=0; i<C; i++) { // 1열부터 순서대로 채움
			for(int j=0; j<R; j++) {
				arr[j][i] = str.charAt(idx++);
			}
		}
		for(int i=0; i<R; i++) { // 1행부터 다시 순서대로 읽음
			for(int j=0; j<C; j++) {
				sb.append(arr[i][j]);
			}
		}
		System.out.println(sb);
	}

}
