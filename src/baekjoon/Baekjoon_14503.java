package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 로봇 청소기
/*
 * 반시계 방향으로 돌면서 점검하고 청소안했으면 전진, 4방향 다 청소했으면 후진하는 방법으로 그리 어렵지 않게 풀었다.
 * 시간복잡도는 고려하지 않았다.
 */
public class Baekjoon_14503 {

	static int N,M,r,c,d;
	static int[][] arr;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 북,동,남,서
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]); // 청소기의 행
		c = Integer.parseInt(str[1]); // 청소기의 열
		d = Integer.parseInt(str[2]); // 청소기가 바라보는 방향 0~3
		
		// 지도 생성
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		arr[r][c] = 2; // 시작칸은 청소하므로 2로 바꿈
		int result = 1; // 1칸 청소하고 시작한다.
		
		while(true) {
			if(clean()) result++; // 청소할 수 있으면 결과값 증가
			else if(!goBack()) break; // 뒤로 갈 수 없으면 반복문 종료
		}
		System.out.println(result);
		
	}
	
	private static boolean clean() {
		int nr,nc;
		for(int i=0; i<4; ++i) { // 왼쪽으로 돌면서 한바퀴 돌떄까지 총4번 확인하므로
			nr = r+dir[(d+3-i)%4][0]; // 왼쪽,뒤쪽,오른쪽,정면 순서대로 4방향 검색
			nc = c+dir[(d+3-i)%4][1];
			if(isDirty(arr[nr][nc])) { //  청소할 인접칸이 존재하면
				r=nr; c=nc; d=(d+3-i)%4; // 좌표와 방향을 움직인 후
				arr[nr][nc] = 2; // 청소한 칸은 2로 변경
				return true; // 움직였으므로 사방탐색 종료
			}
		}
		return false;
	}
	private static boolean goBack() {
		// 4방항 확인 후 청소할 곳이 없으면
		int nr = r+dir[(d+2)%4][0];
		int nc = c+dir[(d+2)%4][1];
		if(arr[nr][nc]!=1) { // 뒤쪽이 벽이 아니면
			r=nr; c=nc; // 후진
			return true;
		}
		return false; // 뒤쪽이 벽이면 false 반환
	}
	private static boolean isDirty(int num) { // 아직 청소를 안 한 곳이면 true 반환
		if(num==0) return true;
		return false;
	}
}
