package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// N번째 큰 수
/*
 * PriorityQueue를 이용하면 기본적으로 오름차순이기때문에,
 * 내림차순으로 힙정렬을 한 후 N번째 원소를 출력하면 된다.
 * 시간복잡도: O(NlogN) 힙정렬
 */
public class Baekjoon_2075 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		int N = Integer.parseInt(br.readLine());
		String[] line;
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			for(int j=0; j<N; ++j) {
				pq.offer(Integer.parseInt(line[j]));
			}
		}
		int times = N;
		while(--times>0) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}
}