package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 보석 도둑
/*
 * 1. 보석을 무게 오름차순, 무게가 같으면 가격 내림차순으로 정렬
 * 2. 가방을 무게 오름차순 정렬
 * 3. 가방 하나당 가능한 모든 보석들을 pq에 가격만 내림차순으로 삽입
 * 4. 가방 하나당 pq에 보석의 가격이 존재하면 poll을 하여 가장 높은 가격을 정답에 더함.(greedy 방식)
 */
public class Baekjoon_1202 {

	static class Jewlry implements Comparable<Jewlry> {
		
		int w,cost;
		
		public Jewlry(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}

		@Override
		public int compareTo(Jewlry o) {
			if(this.w==o.w) { // 무게가 같으면
				return Double.compare(o.cost, this.cost); // 가격 내림차순
			}
			return Integer.compare(this.w, o.w); // 무게가 다르면 무게 오름차순
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		PriorityQueue<Jewlry> jewlryPQ = new PriorityQueue<Jewlry>();
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			int w = Integer.parseInt(str[0]);
			int cost = Integer.parseInt(str[1]);
			jewlryPQ.offer(new Jewlry(w, cost));
		}
		int[] bags = new int[K];
		for(int i=0; i<K; ++i) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags); // 가방 무게 오름차순
		
		PriorityQueue<Integer> costPQ = new PriorityQueue<Integer>(Comparator.reverseOrder()); // 보석 가격 내림차순 정렬
		
		long answer = 0; // long 범위 주의!
		for(int i=0; i<K; ++i) {
			
			while(!jewlryPQ.isEmpty() && jewlryPQ.peek().w <= bags[i]) { // 보석이 아직 남아있고, 보석을 가방에 담을 수 있으면
				costPQ.offer(jewlryPQ.poll().cost); // 보석의 가격을 pq에 담기
			}
			
			if(!costPQ.isEmpty()) {
				answer += costPQ.poll(); // 가장 비싼 가격 더하기
			}
		}
		System.out.println(answer);
	}
}