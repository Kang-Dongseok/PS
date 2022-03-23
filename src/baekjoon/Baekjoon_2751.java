package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 수 정렬하기 2
public class Baekjoon_2751 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		System.out.print(sb.toString());
	}
}