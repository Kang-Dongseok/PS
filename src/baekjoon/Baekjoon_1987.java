package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1987 {

	static int R,C,result;
	static boolean[] isVisited = new boolean[26]; // 알파벳 중복 체크
	static char[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		arr = new char[R][C];
		for(int i=0; i<R; ++i) {
			arr[i] = br.readLine().toCharArray();
		}
		
		
		func(1,0,0); // 시작부터 하나 방문하므로 1부터 시작
		
		System.out.println(result);
	}
	
	public static void func(int n, int r, int c) {
		char ch = arr[r][c];
		isVisited[ch-'A']=true; // 방문체크
		if(isVaild(r-1, c)) func(n+1, r-1, c); // 상
		if(isVaild(r, c+1)) func(n+1, r, c+1); // 우
		if(isVaild(r+1, c)) func(n+1, r+1, c); // 하
		if(isVaild(r, c-1)) func(n+1, r, c-1); // 좌
		isVisited[ch-'A']=false; // 방문체크 해제
		if(result<n) result = n; // 최고값 갱신 후 종료
		return;
		
	}
	public static boolean isVaild(int r, int c) {
		if(r>=0 && r<R && c>=0 && c<C && !isVisited[arr[r][c]-'A']) return true;
		return false;
	}
}
