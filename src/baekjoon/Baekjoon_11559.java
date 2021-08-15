package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
//  Puyo Puyo
/*
 * 시간 복잡도: O(1), 12X6 칸으로 고정이기 때문에
 */
public class Baekjoon_11559 {

	static char[][] arr = new char[12][6];
	static boolean[][] isChecked = new boolean[12][6]; // bfs시 체크 하였는지 확인하는 배열
	static Queue<int[]> q = new ArrayDeque<int[]>(); // 같은 색들의 좌표를 담을 큐
	static boolean isPopped; // 연쇄 횟수가 올라가는지 유무
	static int combo; // 연쇄 횟수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 필드 생성
		for(int i=0; i<12; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		play();
		System.out.println(combo);
	}
	
	public static void play() {
		while(true) {
			// 한 번의 실행마다 전체 좌표 검색하며 1연쇄
			for(int i=11;i>=0;i--) {
				for(int j=0;j<6;j++) {
					popColor(i, j);
				}
			}
			if(!isPopped) break; // 터지지 않으면 바로 종료
			// 터지면
			combo++; // 연쇄횟수 증가
			isPopped = false; // 터짐 유무 초기화
			// 1연쇄 후 중복 체크 배열 초기화
			for(boolean[] a : isChecked) {
				Arrays.fill(a, false);
			}
			moveDown(); // 바닥으로 내리기
		}
	}
	public static void moveDown() { // 중력에 의해 바닥으로 내리기
		for(int col=0; col<6; col++) { // 1열부터 6열까지 한 열씩 내리기
			columnDown(col);
		}
	}
	public static void columnDown(int col) { // 한 열 내리기
		Queue<Character> charQueue = new ArrayDeque<Character>();
		for(int i=11; i>=0; i--) { // 아래서부터 위로 큐에 색깔만 집어넣기
			char ch = arr[i][col];
			if(ch!='.') charQueue.offer(ch);
		}
		for(int i=11; i>=0; i--) { // 아래서부터 위로 필드에 색깔 먼저 집어넣기
			if(!charQueue.isEmpty()) arr[i][col] = charQueue.poll();
			else {
				arr[i][col] = '.';
			}
		}
	}
	public static void popColor(int r, int c) { // 좌표 기준으로 같은 색 4개 이상이면 터트리는 메소드
		if(arr[r][c] == '.') return;
		char color = arr[r][c];
		searchColor(r,c,color);
		if(q.size()<4) q.clear(); // 연속 4개 아니면 큐를 비움
		else { // 연속 4개 이상이면
			while(!q.isEmpty()) {
				int[] point = q.poll(); // 좌표 하나씩 꺼내서
				arr[point[0]][point[1]] = '.'; // 터트림
				isPopped = true; // 연쇄 횟수 올리기 위한 조건
			}
		}
	}
	public static void searchColor(int r, int c, char color) { // bfs이용하여 해당좌표와 붙어있는 같은 색 검색하여 좌료를 큐에 저장
		if(arr[r][c] != color) { // 현재 칸이 탐색중인 색깔과 다르면
			return;
		} else { // 같은 색깔이면 큐에 좌표 저장 && 중복체크
			q.offer(new int[] {r,c});
			isChecked[r][c] = true;
		}
		// 경계 체크 && 중복체크
		if(c>0 && isChecked[r][c-1]==false) searchColor(r, c-1, color); // 왼쪽 탐색
		if(r>0 && isChecked[r-1][c]==false) searchColor(r-1, c, color); // 위쪽 탐색
		if(c<5 && isChecked[r][c+1]==false) searchColor(r, c+1, color); // 오른쪽 탐색
		if(r<11 && isChecked[r+1][c]==false) searchColor(r+1, c, color); // 아래쪽 탐색
	}
}