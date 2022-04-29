package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 이차원 배열과 연산
public class Baekjoon_17140 {
	
	static int R,C;
	static int[][] map;
	static PriorityQueue<int[]> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[100][100];
		String[] str = br.readLine().split(" ");
		int r = Integer.parseInt(str[0]);
		int c = Integer.parseInt(str[1]);
		int k = Integer.parseInt(str[2]);
		for(int i=0; i<3; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<3; ++j) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}else {
					return Integer.compare(o1[1], o2[1]);
				}
			}
		});
		
		R=3; C=3;
		int t = -1;
		while(t++<=100) {
			if(map[r-1][c-1]==k) {
				System.out.println(t);
				return;
			}
			if(R>=C) { // R연산
				calcR();
			}else { // C연산
				calcC();
			}
		}
		System.out.println(-1);
	}

	public static void calcR() {
		C=0;
		for(int r=0; r<R; ++r) {
			pq.clear();
			Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
			for(int c=0; c<100; ++c) {
				if(map[r][c]==0) continue; // 0이면 무시
				hashMap.put(map[r][c], hashMap.getOrDefault(map[r][c], 0)+1);
			}
			for(int key : hashMap.keySet()) {
				pq.offer(new int[] {key,hashMap.get(key)});
			}
			for(int c=0; c<100; c+=2) {
				if(!pq.isEmpty()) {
					int[] cur = pq.poll();
					map[r][c] = cur[0];
					map[r][c+1] = cur[1];
					C=Math.max(C, c+2);
				}else {
					map[r][c] = 0;
					map[r][c+1] = 0;
				}
			}
		}
	}
	public static void calcC() {
		R=0;
		for(int c=0; c<C; ++c) {
			pq.clear();
			Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
			for(int r=0; r<100; ++r) {
				if(map[r][c]==0) continue; // 0이면 무시
				hashMap.put(map[r][c], hashMap.getOrDefault(map[r][c], 0)+1);
			}
			for(int key : hashMap.keySet()) {
				pq.offer(new int[] {key,hashMap.get(key)});
			}
			for(int r=0; r<100; r+=2) {
				if(!pq.isEmpty()) {
					int[] cur = pq.poll();
					map[r][c] = cur[0];
					map[r+1][c] = cur[1];
					R=Math.max(R, r+2);
				}else {
					map[r][c] = 0;
					map[r+1][c] = 0;
				}
			}
		}
	}
}