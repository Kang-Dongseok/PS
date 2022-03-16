package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 감시
public class Baekjoon_15683 {
	

	static int N,M,result=Integer.MAX_VALUE;
	static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌
	static int[][] cctvs = new int[8][3]; //각 행에 cctv의 행,열,번호를 저장
	static int cctvNum; // cctv의 총 갯수
	static int[][] arr;
	static boolean[][] isChecked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		isChecked = new boolean[N][M];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		int idx=0; // cctvs에 cctv정보를 저장하기 위한 인덱스
		for(int i=0; i<N; ++i) { // cctv 정보들을 저장
			for(int j=0; j<M; ++j) {
				int num = arr[i][j];
				if(num!=0 && num!=6) { // cctv이면 행,열,번호를 저장
					cctvs[idx][0] = i;
					cctvs[idx][1] = j;
					cctvs[idx++][2] = num;
				} else if(num==6) {
					isChecked[i][j]=true; // 벽은 사각지대에 포함되지 않으므로 true처리
				}
			}
		}
		cctvNum = idx;
		func(0,isChecked);
		
		System.out.println(result);
	}
	public static void func(int num, boolean[][] arrChecked) {
		
		if(num==cctvNum) { // cctv 전부 다 확인했으면 종료
			boolean[][] copiedArr = myArrCopy(arrChecked);
			int cnt = calcSum(copiedArr);
			if(cnt<result) result=cnt; // 최솟값 갱신
			return;
		}
		int r = cctvs[num][0];
		int c = cctvs[num][1];
		int cctvType = cctvs[num][2]; // cctv 종류: 1~5
		if(cctvType!=0) arrChecked[r][c] = true; // cctv가 존재하면 있는자리 체크
		switch (cctvType) {
		case 1:
			for(int i=0; i<4; ++i) { // 한 방향
				boolean[][] copiedArr = myArrCopy(arrChecked);
				lookOneDir(r, c, i, copiedArr);
				func(num+1,copiedArr);
			}
			break;
		case 2:
			for(int i=0; i<2; ++i) { // 양쪽 방향
				boolean[][] copiedArr = myArrCopy(arrChecked);
				lookOneDir(r, c, i, copiedArr);
				lookOneDir(r, c, i+2, copiedArr);
				func(num+1,copiedArr);
			}
			break;
		case 3:
			for(int i=0; i<4; ++i) { // 90도 방향
				boolean[][] copiedArr = myArrCopy(arrChecked);
				lookOneDir(r, c, i, copiedArr);
				lookOneDir(r, c, (i+1)%4, copiedArr);
				func(num+1,copiedArr);
			}
			break;
		case 4:
			for(int i=0; i<4; ++i) {
				boolean[][] copiedArr = myArrCopy(arrChecked);
				for(int j=0; j<4; ++j) { // 세 방향
					if(i!=j) lookOneDir(r, c, j, copiedArr);
				}
				func(num+1,copiedArr);
			}
			break;
		case 5:
			boolean[][] copiedArr = myArrCopy(arrChecked);
			for(int i=0; i<4; ++i) { // 사방 전부 탐색
				lookOneDir(r, c, i, copiedArr);
			}
			func(num+1,copiedArr);
			break;
		}
	}

	public static int calcSum(boolean[][] arr) {
		int cnt = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(!arr[i][j]) cnt++; // 체크 못한 사각지대 갯수 증가
			}
		}
		return cnt;
	}
	
	public static void lookOneDir(int r, int c, int dir, boolean[][] arrChecked) {
		int nr = r+direction[dir][0];
		int nc = c+direction[dir][1];
		while(isVaild(nr, nc)) {
			arrChecked[nr][nc] = true;
			nr += direction[dir][0];
			nc += direction[dir][1];
		}
	}
	public static boolean isVaild(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<M && arr[r][c]!=6) return true;
		return false;
	}
	public static boolean[][] myArrCopy(boolean[][] arr) {
		boolean[][] copiedArr = new boolean[N][];
		for(int i=0; i<N; ++i) {
			copiedArr[i] = arr[i].clone();
		}
		return copiedArr;
	}
}