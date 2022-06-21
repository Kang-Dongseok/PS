package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 주사위 윷놀이
public class Baekjoon_17825 {

	static int max;
	static int[] dice;
	static int[] horse;
	static ArrayList<Integer>[] list;
	static Map<Integer, Integer> score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dice = new int[10];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<10; ++i) {
			dice[i] = Integer.parseInt(str[i]);
		}
		horse = new int[4]; // 말 4개의 위치
		list = new ArrayList[33];
		for(int i=0; i<33; ++i) {
			list[i] = new ArrayList<Integer>();
		}
		list[0].add(1);
		list[1].add(2);
		list[2].add(3);
		list[3].add(4);
		list[4].add(5);
		list[5].add(6);
		list[6].add(7);
		list[7].add(8);
		list[8].add(9);
		list[9].add(10);
		list[10].add(11);
		list[11].add(12);
		list[12].add(13);
		list[13].add(14);
		list[14].add(15);
		list[15].add(16);
		list[16].add(17);
		list[17].add(18);
		list[18].add(19);
		list[19].add(20);
		list[20].add(32);
		
		list[5].add(21);
		list[21].add(22);
		list[22].add(23);
		list[23].add(24);
		list[24].add(30);
		
		list[10].add(25);
		list[25].add(26);
		list[26].add(24);
		
		list[15].add(27);
		list[27].add(28);
		list[28].add(29);
		list[29].add(24);
		
		list[30].add(31);
		list[31].add(20);
		
		score = new HashMap<Integer, Integer>();
		score.put(1, 2);
		score.put(2, 4);
		score.put(3, 6);
		score.put(4, 8);
		score.put(5, 10);
		score.put(6, 12);
		score.put(7, 14);
		score.put(8, 16);
		score.put(9, 18);
		score.put(10, 20);
		score.put(11, 22);
		score.put(12, 24);
		score.put(13, 26);
		score.put(14, 28);
		score.put(15, 30);
		score.put(16, 32);
		score.put(17, 34);
		score.put(18, 36);
		score.put(19, 38);
		score.put(20, 40);
		score.put(21, 13);
		score.put(22, 16);
		score.put(23, 19);
		score.put(24, 25);
		score.put(25, 22);
		score.put(26, 24);
		score.put(27, 28);
		score.put(28, 27);
		score.put(29, 26);
		score.put(30, 30);
		score.put(31, 35);
		score.put(32, 0);
		
		dfs(0,0);
		System.out.println(max);
	}
	
	public static void dfs(int n, int sum) {
		if(n==10) {
			max = Math.max(max, sum);
			return;
		}
		boolean startFlag = false;
		for(int i=0; i<4; ++i) {
			if(horse[i]==32) continue; // 이미 도착한 말이면 무시
			if(startFlag && horse[i]==0) continue; // 이전에 0에서 움직인 말이 있으면 다른 0에 있는 말은 무시
			int next = getNext(horse[i],dice[n]);
			if(canGo(next)) {
				if(horse[i]==0) startFlag = true; // 0에서 시작하는 말 있으면 체크
				int tmp = horse[i];
				horse[i] = next; // 말 움직이기
				dfs(n+1, sum+score.get(next));
				horse[i] = tmp; // 말 되돌리기
			}
		}
		
	}
	
	public static int getNext(int n, int t) { // n위치에서 t만큼 이동
		int next;
		// 1번 움직임
		if(n==5 || n==10 || n==15) { // 시작이 양갈래 길이면
			next = list[n].get(1); // 파란색 따라가기
		}else {
			next = list[n].get(0);
		}
		// 나머지 t-1번 움직임
		for(int i=0; i<t-1; ++i) {
			if(!list[next].isEmpty()) next = list[next].get(0);
		}
		return next;
	}
	
	public static boolean canGo(int n) {
		if(n==32) return true; // 도착 칸이면 true
		for(int i=0; i<4; ++i) {
			if(horse[i]==n) return false;
		}
		return true;
	}
}