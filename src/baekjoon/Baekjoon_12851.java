package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 숨바꼭질 2
/*
 * bfs를 이용하여 시작점(루트노드)에서 한 depth씩 차례대로 탐색하면 어렵지 않게 해결할 수 있는 문제.
 */
public class Baekjoon_12851 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" "); 
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		boolean[] visited = new boolean[100001];
		if(N>=K) {
			System.out.println(N-K);
			System.out.println(1);
			return;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N); // 시작점 삽입
		int resultTime = 0; // 가장 빠른 시간
		int resultCnt = 0; // 가장 빠른 방법 수
		Set<Integer> visitedPos = new HashSet<Integer>();
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) { // pq에서 같은 depth에 있는 것만 확인
				int pos = q.poll();
				if(pos<0 || pos > 100000) continue; // 범위 밖이면 무시
				if(visited[pos]) continue; // 이미 확인한 지점이면 무시
				visitedPos.add(pos); // 현재 지점을 set에 추가
				if(pos==K) { // K에 도착했으면
					resultCnt++; // 도착 방법 수 1 증가
				}else { // K에 도착하지 않았으면
					q.offer(pos-1); // X-1로 이동
					q.offer(pos+1); // X+1로 이동
					q.offer(pos*2); // X*2로 이동
				}
			}
			if(resultCnt>0) break; // 같은 depth에서 하나라도 도착을 했으면 바로 종료
			resultTime++; // 시간 1초 경과
			for(int n : visitedPos) {
				visited[n]=true; // 방문한 곳들 한번에 체크
			}
			visitedPos.clear(); // 초기화
		}
		System.out.println(resultTime);
		System.out.println(resultCnt);
	}
}