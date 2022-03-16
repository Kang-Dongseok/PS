package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 군사 이동
/*
 * PriorityQueue의 정렬 방법을 Comparator<T>를 이용하여 설정하는 방법을 배웠다.
 * union-find 방법으로도 풀어봐야겠다!
 */
public class Baekjoon_11085 {

	static int p,w,c,v;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<int[]>[] adjList; // 인접리스트
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] line = br.readLine().split(" ");
    	p = Integer.parseInt(line[0]);
    	w = Integer.parseInt(line[1]);
    	line = br.readLine().split(" ");
    	c = Integer.parseInt(line[0]);
    	v = Integer.parseInt(line[1]);
    	
    	distance = new int[p];
    	Arrays.fill(distance, Integer.MAX_VALUE); // 최솟값을 구하기 위해 초기화
    	visited = new boolean[p];
    	adjList = new ArrayList[p];
    	for(int i=0; i<p; ++i) {
    		adjList[i] = new ArrayList<int[]>();
    	}
    	
    	for(int i=0; i<w; ++i) {
    		line = br.readLine().split(" ");
    		int start = Integer.parseInt(line[0]);
    		int end = Integer.parseInt(line[1]);
    		int width = Integer.parseInt(line[2]);
    		adjList[start].add(new int[] {end, width});
    		adjList[end].add(new int[] {start, width});
    	}
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() { // {시작점, 도착점, 길의 너비} 저장
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[2], o1[2]); // 거리기준 내림차순 정렬
			}
		});
    	
    	pq.add(new int[] {c,c,Integer.MAX_VALUE}); // 제일 처음 값은 이후 최솟값 갱신시 비교하는 과정에 영향을 미치기 때문에 문제 조건에 따라 1000이상으로 초기화
    	
    	while(!pq.isEmpty()) {
    		int[] cur = pq.poll();
    		int from = cur[0]; // 시작점
    		int to = cur[1]; // 도착점
    		int dist = cur[2]; // 길의 너비
    		
    		if(visited[to]) continue; // 이미 방문했으면 무시
    		visited[to] = true; // 방문체크
    		
    		distance[to] = Math.min(dist, distance[from]); // 기존의 출발점까지의 최솟값과 비교 후 도착점까지의 최솟값을 갱신
    		
    		if(to==v) break; // v 지점까지 도달했으면 종료
    		
    		for(int[] info : adjList[to]) {
    			if(visited[info[0]]) continue; // 이미 방문했으면 무시
    			pq.add(new int[]{to, info[0], info[1]}); // to를 시작점으로 한 새로운 길들을 pq에 담기
    		}
    	}
    	System.out.println(distance[v]);
    }
}