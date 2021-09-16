package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// 색종이 만들기
public class Baekjoon_2636 {

	static int R,C,hour,cheeseLeftCnt;
	static boolean[][] visited;
	static int[][] map;
	static ArrayList<int[]> cheeseSide = new ArrayList<int[]>(); // 가장자리 없어질 치즈들의 좌표를 저장
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		map = new int[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<C; ++j) {
				map[i][j] = Integer.parseInt(str[j]); // 초기 map 생성
				if(map[i][j]==1) cheeseLeftCnt++;
			}
		}
		
		int result = 0;
		while(cheeseLeftCnt>0) { // 남은 치즈가 아직 있으면
			result = cheeseLeftCnt; // 남은 치즈갯수 저장하고
			meltCheese(); // 치즈 녹이기 실행
		}
		
		System.out.println(hour);
		System.out.println(result);
	}
	private static void meltCheese() {
		findSide(0, 0);
		removeSide();
		for(int i=0; i<R; ++i) { // 방문기록 초기화
			Arrays.fill(visited[i], false);
		}
		hour++; // 걸린시간
	}
	private static void findSide(int r, int c) { // dfs 이용하여 치즈의 가장자리 좌표들 cheeseSide에 저장
		visited[r][c]=true; // 방문체크
		if(map[r][c]==1) {
			cheeseSide.add(new int[] {r,c});
			return;
		}
		
		if(isValid(r-1,c))findSide(r-1,c);
		if(isValid(r,c+1))findSide(r,c+1);
		if(isValid(r+1,c))findSide(r+1,c);
		if(isValid(r,c-1))findSide(r,c-1);
	}
	private static boolean isValid(int r, int c) { // 유효성 체크
		if(r>=0 && c>=0 && r<R && c<C && !visited[r][c]) return true;
		return false;
	}
	private static void removeSide() { // 가장자리 치즈 다 없애기
		cheeseLeftCnt -= cheeseSide.size(); // 남은 치즈 갯수 갱신
		while(!cheeseSide.isEmpty()) {
			int[] cheeseOut = cheeseSide.remove(0); // 삭제할 좌표 꺼내서
			map[cheeseOut[0]][cheeseOut[1]]=0; // 0으로 바꾸기
		}
	}
}
