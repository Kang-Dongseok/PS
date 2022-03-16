package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// ACM Craft
/*
 * 해당 건물을 건설하기 위해 바로 직전에 몇개의 건물이 건설되어야 하는지 저장을 한다.
 * 그리고 이전에 건설될 건물이 없는, 시작점(?)과 같은 건물을부터 건설을 시작하면서,
 * 특정 건물 직전까지 건설이 되었을 때, 해당 건물이 건설되기 위한 조건을 만족시키기 위해 저장했던 건물 갯수를 하나씩 줄여나가면서
 * 0이 되면 바로 직전에 건물들이 모두 건설되었다는 의미이므로 건설을 시작한다.
 * 건설을 진행하면서 시간은 가장 오래 걸린 시간으로 최적화시킨다.
 * 문제에서 요구하는 W번째 건물이 다 지어지면 나머지 건물들은 확일할 필요 없이 종료시킨다.
 */
public class Baekjoon_1005 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; ++tc) {
			line = br.readLine().split(" ");
			int N = Integer.parseInt(line[0]);
			int K = Integer.parseInt(line[1]);
			int[] D = new int[N+1]; // 0번 인덱스 사용x, 각 건물당 건설에 걸리는 시간 저장
			int[] totalTime = new int[N+1]; // index번째 건물이 건설되는 최소 시간
			int[] count = new int[N+1]; // index번째 건물로 들어오는 간선의 갯수
			List<Integer>[] adjList = new LinkedList[N+1]; // {건물번호, 해당건물까지 걸리는 누적 시간}을 저장하는 인접행렬
			for(int i=1; i<N+1; ++i) { // 1~N번까지 생성
				adjList[i] = new LinkedList<Integer>();
			}
			
			line = br.readLine().split(" ");
			for(int i=1; i<N+1; ++i) {
				D[i] = Integer.parseInt(line[i-1]);
			}
			
			for(int i=0; i<K; ++i) {
				line = br.readLine().split(" ");
				int start = Integer.parseInt(line[0]); // 건설순서 X
				int end = Integer.parseInt(line[1]); // 건설순서 Y
				adjList[start].add(end);
				count[end]++; // 들어오는 간선의 갯수 1 증가
			}
			int W = Integer.parseInt(br.readLine());
			Queue<int[]> q = new LinkedList<int[]>();
			for(int i=1; i<N+1; ++i) {
				if(count[i]==0) q.offer(new int[] {i,D[i]}); // 들어오는 간선의 갯수가 0개인 건물, 즉 자신보다 이전 순서의 건물이 없으면 q에 삽입
			}
			
			while(!q.isEmpty()) {
				int[] current = q.poll();
				int num = current[0];
				int weight = current[1];
				if(totalTime[num] < weight) totalTime[num] = weight; // 최소 건설시간 저장
				count[num]--; // num번 건물의 count 1감소
				if(count[num]<=0) { // num번 건물의 이전 건물들이 다 건설되었으면
					if(num==W) break;
					for(int end : adjList[num]) {
						q.offer(new int[] {end,totalTime[num]+D[end]});
					}
				}
			}
			System.out.println(totalTime[W]);
		}
	}
}