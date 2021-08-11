package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 숨바꼭질
/*
 * 노드마다 +1칸 자식, -1칸 자식, x2칸 자식을 계속 가지는 트리를 생성하여 BFS를 실행 
 * 시간복잡도: log3(100000)=O(1) 한 노드당 3배씩 추가되어서 최대 10만개까지 가니까...?
 */
public class Baekjoon_1697 {

	static int[] arr = new int[100001]; // 시작점으로부터 해당 인덱스까지 몇번만에 도착하는지 저장하는 배열
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		if(N>=K) { // 동생이 자신보다 작으면 무조건 -1칸 이동으로만 따라갈 수 있기때문에
			System.out.println(N-K);
			return;
		}
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(N);
		
		int level = 1;  // 몇 번 이동했는가(트리의 깊이)
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(--size>=0) {
				int now = queue.poll();
				for(int i=0; i<3; i++) { // -1, +1, x2 세 번 기록하기 위해
					int next = 0;
					
					if(i==0) { // 다음으로 갈 인덱스 셋 다 검색
						next = now-1;
					} else if(i==1) {
						next = now+1;
					} else {
						next = now*2;
					}
					
					if(next == K) {
						System.out.println(level);
						return;
					}
					if(next>0 && next<arr.length && arr[next]==0) { // 다음칸이 범위안에 있고, 이미 기록된 곳이 아니면
						arr[next] = level;
						queue.offer(next);
					}
				}
			}
			level++;
		}
		
	}

}
