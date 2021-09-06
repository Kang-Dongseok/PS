package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 소문난 칠공주
/*
 * 조합 + bfs를 이용한 좌표들의 인접여부 판단으로 해결하였다.
 * 25칸 중 7칸을 S가 4명 이상 포합되면서 뽑은 조합 => 7칸의 연결 유무를 판단 후 ok면 결과값 1 증가
 * -느낀점
 * ArrayList를 deep Copy 하는 법을 알게 되었다. ==> myCopy 메소드 참고
 * 그리고 순열,조합 등 재귀함수 연습이 정말정말 많이 필요한 것 같다. 너무 실수를 많이한다. 때문에 푸는데 5시간 가까이 걸렸다...
 */
public class Baekjoon_1941 {

	static class Student{
		int x,y;
		char ch;
		public Student(int x, int y, char ch) {
			super();
			this.x = x;
			this.y = y;
			this.ch = ch;
		}
		
	}
	static int cnt; // 인접한 칸의 갯수
	static int result;
	static Student[] students = new Student[25];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++) {
			String str = br.readLine();
			for(int j=0; j<5; j++) {
				students[i*5+j] = new Student(i, j, str.charAt(j));
			}
		}
		comb(0, 0, 0, new ArrayList<int[]>());
		System.out.println(result);
	}
	
	private static void comb(int num, int start, int numS, ArrayList<int[]> arr) { // 조합으로 7명의 학생을 선택
		if(7-num+numS<4) return; // 뽑아야하는 남은 학생 수 + 현재 S학생 수 < 4 일때  ==> 즉, 앞으로 남은 학생 전부다 S여도 4명이 안되면 종료
		if(num==7) { // 7명 다 선택하면
			if(numS>=4) { // S가 4명 이상이면
				if(isConnected(arr)) result++; // 연결되어 있으면 종료
			}
			// S가 4명 이상 아니면 그냥 종료
			return;
		}
		
		for(int i=start; i<25; i++) {
			ArrayList<int[]> copiedArr = myCopy(arr);
			copiedArr.add(new int[] {i/5,i%5}); // 선택한 학생의 좌표를 arr에 저장
			if(students[i].ch=='S') comb(num+1,i+1,numS+1,copiedArr); // S학생을 선택하면 1증가
			else comb(num+1,i+1,numS,copiedArr);
		}
	}
	private static boolean isConnected(ArrayList<int[]> arr) { // 7개의 좌표들이 인접해있는지 판단
		boolean[] visited = new boolean[7];
		Queue<int[]> q = new LinkedList<int[]>();
		
		cnt=1; // 인접칸 갯수 1로 초기화
		q.offer(arr.get(0));
		visited[0] = true;
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];
			for(int i=0; i<7; i++) {
				if(!visited[i]) { // 아직 방문안한곳이 있으면
					int dx = arr.get(i)[0]-r;
					int dy = arr.get(i)[1]-c;
					if(Math.abs(dx)+Math.abs(dy)==1) { // 인접칸이면
						q.offer(arr.get(i));
						visited[i] = true;
						cnt++;
					}
				}
			}
		}
		if(cnt==7) return true; // 7칸이 전부 인접하면
		return false;
	}
	private static ArrayList<int[]> myCopy(ArrayList<int[]> arr) {
		ArrayList<int[]> copiedArr = new ArrayList<int[]>();
		for(int[] a : arr) {
			copiedArr.add(a.clone());
		}
		return copiedArr;
	}
}