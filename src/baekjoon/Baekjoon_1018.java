package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 체스판 다시 칠하기
public class Baekjoon_1018 {

	static int result = 64;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		char[][] map = new char[N][M];
		for(int i=0; i<N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<N-7; ++i) {
			for(int j=0; j<M-7; ++j) {
				startWithB(i,j,map); // 맨 왼쪽 위가 B
				startWithW(i,j,map); // 맨 왼쪽 위가 W
			}
		}
		System.out.println(result);
	}
	private static void startWithB(int r, int c, char[][] map) {
		int cnt = 0;
		for(int i=0; i<8; ++i) {
			for(int j=0; j<8; ++j) {
				if((i+j)%2==0 && map[i+r][j+c]=='W') ++cnt;
				else if((i+j)%2==1 && map[i+r][j+c]=='B') ++cnt;
			}
		}
		if(result>cnt) result=cnt;
	}
	private static void startWithW(int r, int c, char[][] map) {
		int cnt = 0;
		for(int i=0; i<8; ++i) {
			for(int j=0; j<8; ++j) {
				if((i+j)%2==0 && map[i+r][j+c]=='B') ++cnt;
				else if((i+j)%2==1 && map[i+r][j+c]=='W') ++cnt;
			}
		}
		if(result>cnt) result=cnt;
	}
}