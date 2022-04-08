package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 나무 재테크
/*
 * 문제를 해석하는데 조금 오래 걸렸지만,
 * 문제에서 시키는대로 하면 어렵지 않게 해결할 수 있는 구현문제였다.
 */
public class Baekjoon_16235 {

	static int N,M,K;
	static int[][] nutrition; // 남은 영양분
	static PriorityQueue<Integer>[][] treeAges; // 나무들의 나이를 저장
	static int[][] A;
	static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		nutrition = new int[N+1][N+1];
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				nutrition[i][j] = 5; // 양분 초기화
			}
		}
		
		A = new int[N+1][N+1]; // 매년 추가할 양분
		for(int i=1; i<=N; ++i) {
			line = br.readLine().split(" ");
			for(int j=1; j<=N; ++j) {
				A[i][j] = Integer.parseInt(line[j-1]);
			}
		}
		
		treeAges = new PriorityQueue[N+2][N+2]; // 바깥쪽 패딩
		for(int i=0; i<N+2; ++i) {
			for(int j=0; j<N+2; ++j) {
				treeAges[i][j] = new PriorityQueue<Integer>();
			}
		}
		
		// 처음에 나무 심기
		for(int i=0; i<M; ++i) {
			line = br.readLine().split(" ");
			int r = Integer.parseInt(line[0]);
			int c = Integer.parseInt(line[1]);
			int age = Integer.parseInt(line[2]);
			treeAges[r][c].offer(age);
		}
		
		for(int i=0; i<K; ++i) {
			springAndSummer(); // 봄 and 여름
			fall(); // 가을
			winter(); // 겨울
		}
		int result = 0;
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				result += treeAges[i][j].size(); // 나무의 갯수 더하기
			}
		}
		System.out.println(result);
	}
	
	public static void springAndSummer() {
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				PriorityQueue<Integer> nextAges = new PriorityQueue<Integer>();
				int deadSum = 0; // 죽어서 영양분이 될 나무
				while(!treeAges[i][j].isEmpty()) {
					int age = treeAges[i][j].poll(); // 가장 어린 나무 선택
					if(nutrition[i][j]>=age) { // 영양분 충분하면
						nutrition[i][j] -= age; // 나이만큼 감소
						nextAges.offer(age+1); // 1살 증가한 나무 나이 추가
					}else {
						deadSum += age/2; // 죽은 나무 나이의 절반만큼 영양분 증가
					}
				}
				treeAges[i][j] = nextAges;
				nutrition[i][j] += deadSum; // 죽은 나무 영양분만큼 증가
			}
		}
	}
	
	public static void fall() {
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				int cnt = 0; // 번식할 나무의 갯수
				for(int age : treeAges[i][j]) {
					if(age%5==0) { // 나무의 나이가 5의 배수이면
						cnt++; // 갯수 증가
					}
				}
				for(int d=0; d<8; ++d) {
					int nr = i+dir[d][0];
					int nc = j+dir[d][1];
					for(int c=0; c<cnt; ++c) { // 카운트만큼 나이 1인 나무 추가
						treeAges[nr][nc].offer(1);
					}
				}
			}
		}
	}
	
	public static void winter() {
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				nutrition[i][j] += A[i][j]; // 각 칸에 양분 추가
			}
		}
	}
}