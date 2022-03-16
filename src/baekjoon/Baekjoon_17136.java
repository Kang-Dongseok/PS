package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 색종이 붙이기
/*
 * 완탐+백트랙킹 문제인것은 알았지만 구현을 하다가 로직이 꼬여서 멘붕이 와서 구글링을 살짝 참고했다...ㅠㅠ
 * 백트랙킹 조건
 * 1. 기존 최솟값보다 사용한 색종이가 많으면 종료
 * 2. 마지막 1까지 다 확인했으면 나머지 칸은 전부 0이므로 종료
 */
public class Baekjoon_17136 {

	static int result,lastOneIdx;
	static int[][] map = new int[10][10];
	static int[] papersLeft = {0,5,5,5,5,5}; // 1~5크기의 색종이가 남은 갯수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		for(int i=0; i<10; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<10; ++j) {
				int n = Integer.parseInt(str[j]);
				map[i][j] = n;
				if(n==1) lastOneIdx=i*10+j; // 마지막 1의 위치를 1차원 인덱스로 표현
			}
		}
		result = 26; // 최솟값을 위해 전체 색종이 수보다 많게 초기화
		dfs(0,0,0);
		if(result==26) result=-1; // 답이 없으면 -1
		System.out.println(result);
	}
	
	public static void dfs(int r, int c, int count) { 
		if(lastOneIdx<r*10+c) { // 마지막 1이 존재하는 칸까지 다 확인했으면
			result = Math.min(result, count);
			return;
		}
		if(count>=result) return; // 기존의 최솟값보다 크면 종료
		
		if(c>9) { // 행의 마지막 열까지 다 확인했으면
			dfs(r+1,0,count); // 다음 행의 첫칸부터 확인
		}else {
			if(map[r][c]==1) { // 현재 칸을 덮을 수 있으면
				for(int size=5; size>=1; --size) {
					if(papersLeft[size]>0&&canCover(r, c, size)) { // 1이고 size크기의 색종이로 덮을 수 있으면
						papersLeft[size]--; // 남은 색종이 수 1 감소
						cover(r, c, size, 0); // 덮기
						
						dfs(r,c+size,count+1); // 다음 칸부터 시작하고 사용한 색종이 수 1증가
						
						papersLeft[size]++; // 남은 색종이 수 1 증가
						cover(r, c, size, 1); // 덮기 취소
					}
				}
			}else { // 현재 칸을 덮을 수 없으면
				dfs(r,c+1,count); // 다음 칸 확인
			}
		}
	}
	
	public static boolean canCover(int r, int c, int size) {
		for(int i=r; i<r+size; ++i) {
			for(int j=c; j<c+size; ++j) {
				if(i<0 || i>=10 || j<0 || j>=10) return false; // 경계 체크
				if(map[i][j]!=1) return false; // 덮을 수 없는 곳이면 false
			}
		}
		return true;
	}
	
	public static void cover(int r, int c, int size, int n) {
		for(int i=r; i<r+size; ++i) {
			for(int j=c; j<c+size; ++j) {
				map[i][j] = n;
			}
		}
	}
}