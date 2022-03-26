package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 마법사 상어와 비바라기
public class Baekjoon_21610 {

	static int N,M;
	static int[][] map;
	static boolean[][] existed;
	static ArrayList<int[]> cloudPos = new ArrayList<int[]>();
	static int[][] dir = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][N];
		existed = new boolean[N][N];
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		// 초기 구름 생성
		cloudPos.add(new int[] {N-2,0});
		cloudPos.add(new int[] {N-2,1});
		cloudPos.add(new int[] {N-1,0});
		cloudPos.add(new int[] {N-1,1});
		for(int i=0; i<M; ++i) {
			line = br.readLine().split(" ");
			int d = Integer.parseInt(line[0]);
			int s = Integer.parseInt(line[1])%N; // 0~N-1칸 이동
			moveCloud(d,s); // 1. 구름 움직이기
			rain(); // 2. 물 1씩 비내리기
			copyWater(); // 4. 주변의 물 복사
			makeNewCloud(); // 5. 특정구역 2감소하고 새로운 구름 만들기
		}
		int answer = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}
	
	public static void moveCloud(int d, int s) {
		for(int[] pos : cloudPos) {
			pos[0] += dir[d][0]*s; // 행 이동
			pos[0] += N; // 음수일 경우
			pos[0] %= N;
			pos[1] += dir[d][1]*s; // 열 이동
			pos[1] += N; // 음수일 경우
			pos[1] %= N;
		}
	}
	
	public static void rain() {
		for(int[] pos : cloudPos) {
			map[pos[0]][pos[1]]++;
		}
	}
	
	public static void copyWater() {
		for(int[] pos : cloudPos) {
			int r = pos[0];
			int c = pos[1];
			if(r>0 && c>0 && map[r-1][c-1]!=0) map[r][c]++;
			if(r>0 && c<N-1 && map[r-1][c+1]!=0) map[r][c]++;
			if(r<N-1 && c>0 && map[r+1][c-1]!=0) map[r][c]++;
			if(r<N-1 && c<N-1 && map[r+1][c+1]!=0) map[r][c]++;
		}
	}

	public static void makeNewCloud() {
		for(int[] pos : cloudPos) {
			int r = pos[0];
			int c = pos[1];
			existed[r][c] = true;
		}
		cloudPos.clear(); // 구름 초기화
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(existed[i][j]) { // 이전 구름 위치이면
					existed[i][j] = false; // 위치 삭제
				}else { // 이전 구름 위치 아니면
					if(map[i][j]>1) { // 2이상이면
						map[i][j] -= 2;
						cloudPos.add(new int[] {i,j});
					}
				}
			}
		}
	}
}