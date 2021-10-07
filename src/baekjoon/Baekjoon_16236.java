package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 아기 상어
public class Baekjoon_16236 {

	
	static int N,result;
	static int sharkR,sharkC,sharkSize; // 상어위치의 행,열, 상어의 사이즈
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Fish> pq;
	
	static class Fish implements Comparable<Fish>{
		int r,c;
		
		public Fish(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.r!=o.r) return Integer.compare(this.r, o.r); // 행 기준 오름차순
			else return Integer.compare(this.c, o.c); // 행이 같으면 열 기준 오름차순
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		sharkSize = 2; // 초기의 상어 크기
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				int val = Integer.parseInt(str[j]);
				map[i][j] = val;
				if(val==9) { // 상어를 찾아서 위치 저장
					sharkR = i;
					sharkC = j;
				}
			}
		}
		findNearestFish(); // 가장 가까운 물고기 찾기
		// 이동거리 카운트하고, 위치 옮기고, 사이즈 증가하는지 확인하기 
	}
	public static void findNearestFish() {
		
	}
}