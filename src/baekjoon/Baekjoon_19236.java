package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 청소년 상어
/*
 * 단순 시뮬문제이지만, 물고기들의 방향과 위치 등 정보를 관리하는 것, 물고기 번호순서대로 움직이는 것,
 * 그리고 상어를 어떻게 판별할 것인지도 조금 생각을 하게 되는 문제였다. (상어를 map에서 -1로 표시했음)
 * 시간복잡도는 계산하지 않았다.
 */
public class Baekjoon_19236 {
	
	static class Fish {
		int num,r,c,dir; // 번호,행,열,방향
		boolean isAlive; // 생존 여부
		public Fish(int num, int r, int c, int dir, boolean isAlive) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}
	
	static int result;
	static int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}}; // 0~7인덱스까지 반시계 방향
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[4][4];
		Fish[] fishInfo = new Fish[17]; // 1~16번의 물고기 저장
		
		String[] str;
		for(int i=0; i<4; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<4; ++j) {
				int num = Integer.parseInt(str[2*j]);
				map[i][j] = num;
				fishInfo[num] = new Fish(num, i, j, Integer.parseInt(str[2*j+1])-1, true); // 방향은 0~7로 설정
			}
		}
		func(0,0,0,map,fishInfo);
		System.out.println(result);
	}
	public static void func(int r, int c, int sum, int[][] map, Fish[] fishInfo) {
		int num = map[r][c];
		sum += num; // 해당 물고기를 먹어서 총합을 증가
		result = Math.max(result, sum); // 최댓값 갱신
		fishInfo[num].isAlive = false; // 해당 물고기 사망
		
		map[r][c] = -1; // 상어를 -1로 표시 (물고기 이동을 위해)
		moveFish(map,fishInfo); // 물고기들 이동
		map[r][c] = num; // 상어가 있는 곳을 다시 물고기로 표시(하지만 죽었음)
		
		int sharkDir = fishInfo[num].dir; // 상어의 방향 정하기
		int nr = r+dir[sharkDir][0];
		int nc = c+dir[sharkDir][1];
		while(nr>=0 && nr<4 && nc>=0 && nc<4) { // 상어가 해당 방향으로 갈 수 있으면
			if(fishInfo[map[nr][nc]].isAlive) { // 물고기를 먹을 수 있으면
				// map 복사
				int[][] copiedMap = arrCopy(map);
				// fishInfo 복사
				Fish[] copiedFishInfo = fishCopy(fishInfo);
				func(nr,nc,sum,copiedMap,copiedFishInfo);
			}
			// 다음 좌표 진행
			nr += dir[sharkDir][0];
			nc += dir[sharkDir][1];
		}
	}
	public static void moveFish(int[][] map, Fish[] fishInfo) {
		for(int i=1; i<=16; ++i) {
			if(!fishInfo[i].isAlive) continue; // 물고기가 죽었으면 패스
			
			int r = fishInfo[i].r;
			int c = fishInfo[i].c;
			int d = fishInfo[i].dir;
			
			for(int j=0; j<8; ++j) { // 8방 탐색
				// 다음 방향을 현재 방향에서 0~7 만큼 반시계 방향으로 회전하며 modulus 연산
				int nr = r+dir[(d+j)%8][0];
				int nc = c+dir[(d+j)%8][1];
				if(nr>=0 && nr<4 && nc>=0 && nc<4 && map[nr][nc] != -1) { // 칸이 있고, 상어가 아니면
					int nextNum = map[nr][nc]; // 바꿀 방향의 물고기번호
					// 현재 물고기의 방향 바꾸기
					fishInfo[i].dir=(d+j)%8;
					// 물고기의 좌표 서로 바꾸기
					fishInfo[nextNum].r=r;
					fishInfo[nextNum].c=c;
					fishInfo[i].r=nr;
					fishInfo[i].c=nc;
					
					// 두 물고기의 위치 바꾸기
					map[r][c]=nextNum;
					map[nr][nc]=i;
					
					break; // 다음 번호의 물고기로 건너뛰기
				}
			}
		}
	}
	public static int[][] arrCopy(int[][] map) { // 배열 깊은 복사
		int[][] copiedArr = new int[4][4];
		for(int i=0; i<4; ++i) {
			for(int j=0; j<4; ++j) {
				copiedArr[i][j] = map[i][j];
			}
		}
		return copiedArr;
	}
	public static Fish[] fishCopy(Fish[] fishInfo) { // Fish배열 깊은 복사
		Fish[] copiedFishInfo = new Fish[17];
		for(int i=1; i<=16; ++i) {
			Fish fish = fishInfo[i];
			copiedFishInfo[i] = new Fish(fish.num, fish.r, fish.c, fish.dir, fish.isAlive);
		}
		return copiedFishInfo;
	}
}