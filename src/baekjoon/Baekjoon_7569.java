package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 토마토
public class Baekjoon_7569 {

	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] str = br.readLine().split(" ");
    	int M = Integer.parseInt(str[0]);
    	int N = Integer.parseInt(str[1]);
    	int H = Integer.parseInt(str[2]);
    	int initCnt = 0; // 초기에 안 익은 토마토 갯수
    	Queue<int[]> q = new LinkedList<int[]>(); // {r,c,h}
    	int[][][] map = new int[N][M][H];
    	for(int i=0; i<H; ++i) {
    		for(int j=0; j<N; ++j) {
    			str = br.readLine().split(" ");
    			for(int k=0; k<M; ++k) {
    				map[j][k][i] = Integer.parseInt(str[k]);
    			}
    		}
    	}
    	for(int i=0; i<N; ++i) {
    		for(int j=0; j<M; ++j) {
    			for(int k=0; k<H; ++k) {
    				if(map[i][j][k]==1) q.offer(new int[] {i,j,k});
    				if(map[i][j][k]==0) initCnt++;
    			}
    		}
    	}
    	if(initCnt==0) { // 모두다 익었으면
    		System.out.println(0);
    		return;
    	}
    	boolean[][][] visited = new boolean[N][M][H];
    	int day = 0;
    	int changeCnt = 0; // 안익은 -> 익은 토마토
    	while(!q.isEmpty()) {
    		boolean isChanged = false;
    		int size = q.size();
    		while(size-->0) {
    			int[] cur = q.poll();
    			int r = cur[0];
    			int c = cur[1];
    			int h = cur[2];
    			
    			if(visited[r][c][h]) continue;
    			visited[r][c][h] = true;
    			if(map[r][c][h]==0) {
    				changeCnt++; // 토마토 익었으면 갯수 증가
    				isChanged = true; // 바뀐거 체크
    			}
    			
    			for(int d=0; d<4; ++d) { // 4방탐색
    				int nr = r+dir[d][0];
    				int nc = c+dir[d][1];
    				if(nr<0 || nr>=N || nc<0 || nc>=M || visited[nr][nc][h]) continue; // 경계 및 방문
    				if(map[nr][nc][h]==0) q.offer(new int[] {nr,nc,h});
    			}
    			// 아래 탐색
    			if(h>0) {
    				if(!visited[r][c][h-1] && map[r][c][h-1]==0) q.offer(new int[] {r,c,h-1});
    			}
    			// 위 탐색
    			if(h<H-1) {
    				if(!visited[r][c][h+1] && map[r][c][h+1]==0) q.offer(new int[] {r,c,h+1});
    			}
    		}
    		if(isChanged) { // 익은 토마토가 있으면
    			day++; // 하루 증가
    		}
    	}
    	if(initCnt!=changeCnt) { // 초기에 안익은 토마토가 전부 다 익지 않았으면
    		System.out.println(-1);
    	}else { // 다 익었으면
    		System.out.println(day);
    	}
    }
}