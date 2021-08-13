package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 캐슬 디펜스
public class Baekjoon_17135 {

	static int N,M,D;
	static int[][] arr; // 격자판
	static int killCnt; // 각 케이스마다 죽은 적의 수
	static int maxKillCnt = Integer.MIN_VALUE; // 전체 케이스 죽은 적의 최대 수
	static Queue<int[]> enemy = new ArrayDeque<int[]>(); // 죽일 적들의 좌표
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		D = Integer.parseInt(str[2]);
		// 격자판 생성 후 적 배치
		arr = new int[N+1][M];
		for(int i=0;i<N;i++) {
			str = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		// 궁수 3명 배치
		// N개 중 3개를 뽑는 조합이므로 3중 for문 이용
		for(int i=0; i<M-2; i++) {
			for(int j=i+1; j<M-1; j++) {
				for(int k=j+1; k<M; k++) {
					int[][] copiedArr = myArrCopy(arr); // 궁수들의 케이스마다 시작 맵 초기화 상태로 저장
					killCnt=0; // 궁수 배치하는 각 경우마다 죽인 적의 수 초기화
					for(int n=0;n<N;n++) {
						func(copiedArr,N,i); func(copiedArr,N,j); func(copiedArr,N,k); // 각각의 궁수마다 죽일 적의 좌표 계산하여 enemy에 저장
						func2(copiedArr); // enemy에 저장된 좌표의 적 죽이기
						move(copiedArr); // 적들 한칸 전진
					}
					if(killCnt>maxKillCnt) maxKillCnt = killCnt;
				}
			}
		}
		System.out.println(maxKillCnt);
	}
	
	public static void move(int[][] array) { // 적들 한칸씩 전진
		for(int i=N-1; i>=1; i--) {
			array[i] = array[i-1];
		}
		array[0] = new int[M];
	}
	public static int[][] myArrCopy(int[][] arr){ // 2차원 배열 깊은 복사
		int[][] copiedArr = new int[arr.length][];
		for(int i=0;i<arr.length;i++) {
			copiedArr[i] = arr[i].clone();
		}
		return copiedArr;
	}
	
	public static void func2(int[][] array) { // 적들 죽이기
		while(!enemy.isEmpty()) {
			int[] point = enemy.poll(); // 죽일 적들의 좌표를 하나씩 꺼내서 0으로 바꿈
			if(array[point[0]][point[1]] == 1) killCnt++;
			array[point[0]][point[1]] = 0;
		}
		
	}
	public static void func(int[][] array,int r, int c) { // bfs를 이용하여 가장 가깝고 왼쪽인 적 찾아서 enemy에 좌표 저장
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r-1,c}); // 가장 가까운 적의 위치(root노드): 궁수 바로 윗칸 좌표
		
		int level = D; // 궁수의 최대 공격 거리
		int size = 0;
		while(--level>=0) {
			size = q.size();
			
			while(--size>=0) { // 
				int[] now = q.poll();
				if(array[now[0]][now[1]] == 1) { // 적을 발견하면
					enemy.offer(now); // 죽일 적의 좌표 저장 후 종료
					return;
				}
				if(now[1]>0) q.offer(new int[] {now[0],now[1]-1}); // 왼쪽 검색
				if(now[0]>0) q.offer(new int[] {now[0]-1,now[1]}); // 윗쪽 검색
				if(now[1]<M-1) q.offer(new int[] {now[0],now[1]+1}); // 오른쪽 검색
			}
		}
	}
}
