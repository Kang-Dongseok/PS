package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 마법사 상어와 블리자드
/*
 * 상어를 시작점으로 어떻게 토네이토 모양처럼 탐색할지 고민하다가
 * 2차원 좌표들을 1차원 좌표로 바꾸어 1차원배열의 처음부터 끝까지 순회하는 방식으로 하였다.
 * 구현해야할 것들이 많아서 약간 난이도가 높았던 문제인 것 같다.
 */
public class Baekjoon_21611 {

	static int N,M,startR,startC,result;
	static int[][] map;
	static int[] map1D; // 2차원 map 배열의 좌표를 1차원 배열의 좌표로 바꿈
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // 상,하,좌,우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][N];
		map1D = new int[N*N];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		startR = N/2; // 초기위치의 행
		startC = N/2; // 초기위치의 열
		mapTo1D();
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int d = Integer.parseInt(str[0])-1; // 인덱스와 맞추기 위해 1 빼줌
			int s = Integer.parseInt(str[1]); 
			ice(d,s); // 얼음 파편
			comeOn(); // 구슬 앞으로 땡겨서 빈칸 채우기
			while(burst()) { // 터질 구슬이 있을 때 까지
				comeOn(); // 구슬 앞으로 당기기
			}
			change(); // 구슬이 그룹에 따라 모양이 바뀐다
		}
		System.out.println(result);
	}
	public static void change() {
		int[][] newMap = new int[N][N];
		int cnt = 1; // 연속된 구슬의 수
		int newIdx = 1; // newMap에 저장할 구슬의 인덱스
		for(int i=2; i<N*N; ++i) { // 이전칸과 비교하기 위해 2부터 시작
			if(newIdx==N*N) break; // newMap에 이미 구슬을 다 채웠으면 끝
			int before = map1D[i-1]; // 이전칸
			int now = map1D[i]; // 현재칸
			if(map[now/N][now%N]==map[before/N][before%N]) { // 이전칸과 같은 구슬이면
				cnt++;
			}else { // 이전칸과 다른 구슬이면
				int pos = map1D[newIdx++];
				newMap[pos/N][pos%N]=cnt; // 갯수 저장
				pos = map1D[newIdx++];
				newMap[pos/N][pos%N]=map[before/N][before%N]; // 구슬번호 저장
				cnt = 1; // 연속된 갯수 1로 갱신
				if(map[now/N][now%N]==0) break; // 0이면 더 이상 구슬이 없으므로 종료
			}
		}
		map = newMap; // 새로 바뀐 newMap으로 교체
	}
	public static boolean burst() { // 연속된 구슬이 있으면 폭파시키고 true 리턴, 없으면 false 리턴
		boolean flag = false;
		int cnt = 1; // 연속된 구슬의 수
		int startIdx = 1; // 연속된 구슬의 시작 인덱스
		for(int i=2; i<N*N; ++i) { // 이전칸과 비교하기 위해 2부터 시작
			int before = map1D[i-1]; // 이전칸
			int now = map1D[i]; // 현재칸
			if(map[now/N][now%N]==map[before/N][before%N]) { // 이전칸과 같은 구슬이면
				cnt++;
			}else { // 이전칸과 다른 구슬이면
				if(cnt>=4) { // 4개 이상 같은 구슬이면
					result += map[before/N][before%N]*cnt; // 폭발한 구슬의 점수 더해주기
					for(int j=startIdx; j<=i-1; ++j) { // 연속된 구슬을 폭파
						int pos = map1D[j];
						map[pos/N][pos%N]=0;
					}
					flag = true;
					if(map[now/N][now%N]==0) break; // 0이면 종료
				}
				// 4개 미만이면 아무 일 없음
				cnt = 1; // 연속된 갯수 1로 갱신
				startIdx = i; // 시작 인덱스 갱신
			}
		}
		return flag;
	}
	public static void comeOn() {
		int afterIdx = 1; // 새로 채워질 칸을 가리킬 인덱스
		for(int beforeIdx=1; beforeIdx<N*N; ++beforeIdx) {
			int before = map1D[beforeIdx]; // 기존칸의 좌표
			int after = map1D[afterIdx]; // 새로 채울칸의 좌표
			if(map[before/N][before%N]!=0) { // 기존칸에 구슬이 있으면
				map[after/N][after%N] = map[before/N][before%N]; // 새로 채워져야 햘 칸에 저장
				afterIdx++; // 새로 채울칸도 다음으로 이동
			}
			// 기존칸에 구슬이 없으면
			// 새로채울칸은 그대로 있는채로 기존의 칸만 자동으로 다음칸으로 넘어간다.
		}
		// 구슬의 빈칸을 다 앞으로 땡기고 난 후에 나머지는 빈칸으로 바꾸어주어야 하므로
		for(int i = afterIdx; i<N*N; ++i) {
			int after = map1D[i];
			map[after/N][after%N]=0;
		}
	}
	public static void ice(int d, int s) { // 얼음 파편 던져서 구슬 0으로 바꾸어 없애기
		int r = startR; // 처음 행
		int c = startC; // 처음 열
		for(int i=0; i<s; ++i) {
			r += dir[d][0]; // 다음 행
			c += dir[d][1]; // 다음 열
			map[r][c] = 0; // 구슬 없애기
		}
	}
	public static void mapTo1D() { // 2차원 map 배열의 좌표를 1차원 배열의 좌표로 바꾸어 저장
		int idx = 0; // 1차원 배열 map1D에 저장할 인덱스
		int r = startR; // 처음 행
		int c = startC; // 처음 열
		map1D[idx++] = (r*N)+c; // 2차원을 1차원 좌표로 변환
		for(int i=1; i<=N/2; ++i) {
			c--; // 왼쪽 한칸을 다음칸으로
			map1D[idx++] = (r*N)+c;
			while(r<(N/2)+i) { // 중심에서 i만큼 떨어진 테두리까지
				r++; /// 아래칸을 다음칸으로
				map1D[idx++] = (r*N)+c;
			}
			while(c<(N/2)+i) {
				c++; // 오른쪽칸을 다음칸으로
				map1D[idx++] = (r*N)+c;
			}
			while(r>(N/2)-i) {
				r--; /// 윗칸을 다음칸으로
				map1D[idx++] = (r*N)+c;
			}
			while(c>(N/2)-i) {
				c--; // 왼쪽칸을 다음칸으로
				map1D[idx++] = (r*N)+c;
			}
		}
	}
}