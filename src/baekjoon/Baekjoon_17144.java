package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 미세먼지 안녕!
public class Baekjoon_17144 {

	static int R,C,T;
	static int rUp,rDown; // 공기청정기 윗행,아랫행 인덱스
	static int[][] room; // 방의 정보
	static int[][] spreadMap; // 미세먼지 확산 증감 map
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		T = Integer.parseInt(str[2]);
		// 방의 정보 생성
		room = new int[R][C];
		spreadMap = new int[R][C];
		for(int i=0; i<R; ++i) {
			str = br.readLine().split(" ");
			for(int j=0;j<C;j++) {
				room[i][j] = Integer.parseInt(str[j]);
			}
		}
		// 공기청정기 행 인덱스 찾기
		for(int r=2; r<R; ++r) {
			if(room[r][0]==-1) {
				rUp = r; rDown = r+1;
				break;
			}
		}
		// T초만큼 확산 후 이동 반복하기
		for(int t=0; t<T; ++t) {
			for(int i=0; i<R; ++i) { // 확산 계산
				for(int j=0;j<C;j++) {
					spreadDust(i, j);
				}
			}
			calcSpreadDust(); // 확산
			move(); // 이동
		}
		
		calcDustAmount();
		System.out.println(result);
	}
	
	private static void spreadDust(int r, int c) { // 먼지 확산 양 계산
		int num = room[r][c];
		int cnt = 0; // 확산되는 방향 갯수
		if(num>0) {
			if(r>0 && room[r-1][c] != -1) { // 공기 청정기 아닌 빈칸이면
				spreadMap[r-1][c] += num/5; // 상
				cnt++;
			}
			if(c<C-1 && room[r][c+1] != -1) {
				spreadMap[r][c+1] += num/5; // 우
				cnt++;
			}
			if(r<R-1 && room[r+1][c] != -1) {
				spreadMap[r+1][c] += num/5; // 하
				cnt++;
			}
			if(c>0 && room[r][c-1] != -1) {
				spreadMap[r][c-1] += num/5; // 좌
				cnt++;
			}
			room[r][c] -= (num/5)*cnt; // 확산 후 줄어든 미세먼지 양
		}
	}
	private static void calcSpreadDust() { // 확산 양 계산 후 확산시키기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				room[i][j] += spreadMap[i][j]; // 먼지 확산 계산
				spreadMap[i][j] = 0; // 확산 map 초기화
			}
		}
	}
	private static void move() {
		// 공기청정기 윗쪽 반시계 방향
		for(int r=rUp-2; r>=0; --r) { // 좌변 아래로 이동
			room[r+1][0] = room[r][0];
		}
		for(int c=1; c<C; ++c) { // 윗변 왼쪽으로 이동
			room[0][c-1] = room[0][c];
		}
		for(int r=1; r<=rUp; ++r) { // 우변 위로 이동
			room[r-1][C-1] = room[r][C-1];
		}
		for(int c=C-2; c>0; --c) { // 아랫변 오른쪽으로 이동
			room[rUp][c+1] = room[rUp][c];
		}
		room[rUp][1] = 0; // 한칸 씩 움직이면 첫칸은 0
		// 공기청정기 아랫쪽 시계 방향
		for(int r=rDown+2; r<R; ++r) { // 좌변 위로 이동
			room[r-1][0] = room[r][0];
		}
		for(int c=1; c<C; ++c) { // 아랫변 왼쪽으로 이동
			room[R-1][c-1] = room[R-1][c];
		}
		for(int r=R-2; r>=rDown; --r) { // 우변 아래로 이동
			room[r+1][C-1] = room[r][C-1];
		}
		for(int c=C-2; c>0; --c) { // 윗변 오른쪽으로 이동
			room[rDown][c+1] = room[rDown][c];
		}
		room[rDown][1] = 0; // 한칸 씩 움직이면 첫칸은 0
	}
	private static void calcDustAmount() { // 남은 먼지양 계산
		for(int r=0; r<R; ++r) {
			for(int c=0; c<C; ++c) {
				result += room[r][c];
			}
		}
		result += 2; // 공기청정기 값 -2 제외하기
	}
}
