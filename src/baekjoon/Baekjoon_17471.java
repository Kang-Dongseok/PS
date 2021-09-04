package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 게리맨더링
/*
 * 편의상 선거구 A,B로 나눔
 * 
 */
public class Baekjoon_17471 {

	static int N;
	static int result = Integer.MAX_VALUE;
	static int[] numOfPeople;
	static boolean[][] matrix; // 가중치 없는 무향 인접행렬
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numOfPeople = new int[N+1];
		matrix = new boolean[N+1][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i<=N; ++i) {
			numOfPeople[i] = Integer.parseInt(st.nextToken()); // 인구 수 저장
		}
		for(int i=1; i<=N; ++i) { // 인접행렬 생성
			st = new StringTokenizer(br.readLine()," ");
			int num = Integer.parseInt(st.nextToken()); // 구역 번호
			for(int j=0; j<num; j++) {
				matrix[i][Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		for(int total=1; total<=N/2; ++total) { // A선거구를 기준으로 i개 만큼의 구역을 뽑는다, A,B가 서로 인구수만 비교하므로 N/2개 까지만 뽑으면 된다.
			selectA(1,total,new ArrayList<Integer>());
		}
		if(result==Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	private static void selectA(int start, int total, ArrayList<Integer> A) { // 조합을 이용하여 A선거구의 구역들을 선택
		if(A.size()==total) { // 원하는 갯수만큼 다 뽑았으면
			func(A); // A연결 유무, B연결 유무 판단 후 인구 차이 계산
			return;
		}
		
		for(int i=start; i<=N; ++i) {
			A.add(i);
			selectA(i+1,total,A);
			A.remove(A.size()-1);
		}               
	}
	private static void func(ArrayList<Integer> A) {
		if(!isConnected(A)) return; // A선거구 연결 안되어 있으면 종료

		// B선거구 나머지 구역 선택하기
		ArrayList<Integer> B = new ArrayList<Integer>();
		for(int i=1; i<=N; ++i) {
			if(!A.contains(i)) B.add(i); // A선거구에 없으면 B선거구에 추가
		}
		
		if(!isConnected(B)) return; // B선거구 연결 안되어 있으면 종료
		
		// 둘 다 유효한 선거구이면
		int numTotal = 0; // 전체 인구
		int numA = 0; // A선거구 총 인구
		for(int i=1; i<=N; ++i) {
			numTotal += numOfPeople[i];
			if(A.contains(i)) numA += numOfPeople[i];
		}
		int diff = Math.abs(numA - (numTotal-numA));
		if(result>diff) result = diff; // 최솟값 갱신
		
	}
	private static boolean isConnected(ArrayList<Integer> A) { // 해당 선거구의 연결 유무
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		
		int start = A.get(0); // 선거구의 첫 구역을 시작점으로 bfs
		int cnt = 1;
		
		visited[start] = true;
		q.offer(start);
		while(!q.isEmpty()) {
			int num = q.poll();
			for(int i=1; i<=N; ++i) {
				if(!visited[i] && matrix[num][i] && A.contains(i)) { // 방문안했고, 간선이 존재하며, 선거구에 포함되면
					q.offer(i);
					visited[i]=true;
					cnt++;
				}
			}
		}
		if(cnt==A.size()) return true; // 구역 갯수만큼 방문했으면, 구역이 전부 연결되어 있으므로
		return false;
	}
}
