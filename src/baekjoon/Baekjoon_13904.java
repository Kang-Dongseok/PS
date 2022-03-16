package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 과제
/*
 * 그리디를 이용하여
 * 마감일 기준 오름차순 정렬하여 앞에서 부터 하나씩 과제를 점수기준으로 최소힙에 저장하는데,
 * 마감일이 같으면 앞의 과제중에 최소점수랑 비교하여 더 높으면 과제를 선택, 아니면 그냥 버린다.
 * 시간 복잡도: O(NlogN) 힙을 이용했기 때문에?
 */
public class Baekjoon_13904 {

	static int N;
	static int[][] hw; // 과제들의 마감기한과 점수를 저장
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		hw = new int[N][];
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			hw[i] = new int[] {Integer.parseInt(str[0]),Integer.parseInt(str[1])};
		}
		Arrays.sort(hw,(o1,o2)->(o1[0]-o2[0])); // 2차원 배열을 0열 기준으로 오름차순 정렬
		
		int result=0;
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(); // 최소힙
		for(int i=0; i<N; ++i) {
			if(hw[i][0]>pQueue.size()) {
				pQueue.offer(hw[i][1]);
			} else {
				if(hw[i][1]>pQueue.peek()) { // 기존의 과제중에 최소점수보다 새로운 과제의 점수가 크면
					pQueue.poll(); // 최소점수 꺼내고
					pQueue.offer(hw[i][1]); // 더 큰 점수 삽입
				}
			}
		}
		for(int n : pQueue) {
			result += n;
		}
		System.out.println(result);
	}
	
}