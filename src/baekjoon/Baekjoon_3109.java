package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 빵집
/*
 * 백트랙킹 이용하여 해결
 * 백트래킹시 답을 찾으면 재귀함수가 어떤식으로 리턴해야하는지 생각해볼 것!
 */
public class Baekjoon_3109 {

	static int R,C,cnt;
	static char[][] arr;
	static boolean[][] isChecked; // 탐색 중복 체크
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		arr = new char[R][C];
		isChecked = new boolean[R][C];
		
		for(int i=0; i<R; ++i) {
			arr[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<R; ++i) {
			func(i,0);
		}
		
		System.out.println(cnt);
		
	}
	
	public static boolean func(int r, int c) {
		isChecked[r][c] = true;
		if(c==C-1) { // C개만큼 파이프를 설치하면 종료
			cnt++; // 파이프라인 하나 생성하고 종료
			return true;
		}
		// 빵집까지 도착하지 않았으면
		if(isVaild(r-1, c+1)) { // 우 상향
			if(func(r-1, c+1)) return true; // 길을 찾으면 true 반환
		}
		if(isVaild(r, c+1)) { // 우
			if(func(r, c+1)) return true; // 길을 찾으면 true 반환
		}
		if(isVaild(r+1, c+1)) { // 우 하향
			if(func(r+1, c+1)) return true; // 길을 찾으면 true 반환
		}
		return false;
	}
	public static boolean isVaild(int r, int c) {
		if(r>=0 && r<R && c<C && !isChecked[r][c] && arr[r][c]=='.') return true; // 위,아래,오른쪽 경계 안벗어나면
		return false;
	}
}