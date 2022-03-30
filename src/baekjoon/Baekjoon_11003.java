package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 최솟값 찾기
/*
 * 이 문제의 핵심은 매번 새로 들어오는 숫자보다 작은 숫자만 남겨놓으면 된다.
 * 가장 최근의 숫자부터 비교해가며 새로운 수보다 작은수가 나올때까지 제거한다.
 * 그러면 어차피 큰수들은 필요없으므로 제외되고, 자동적으로 제일 앞에 있는 수가 최솟값이 될것이다.
 * 왜냐하면 이전부터 계속 새로운 수보다 작은 수만 남겨놓았으므로.
 * 물론 범위밖의 숫자들은 없애주어야 하므로, 이 용도를 위해 숫자와 함께 인덱스도 같이 저장한다.
 * 시간복잡도: O(N), 슬라이딩 윈도우 기법
 */
public class Baekjoon_11003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<int[]> deq = new ArrayDeque<int[]>(); // {해당숫자, 인덱스} 저장
		StringBuilder sb = new StringBuilder();
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int L = Integer.parseInt(line[1]);
		int[] arr = new int[N+1];
		line = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i+1] = Integer.parseInt(line[i]);
		}
		for(int i=1; i<N+1; ++i) {
			int nextNum = arr[i];
			while(!deq.isEmpty() && deq.peekLast()[0]>nextNum) { // 새로운수보다 마지막 수가 크면
				deq.pollLast(); // 큰 수들 다 제거
			}
			deq.offer(new int[] {nextNum,i}); // 새로운 수와 인덱스 추가
			if(deq.peekFirst()[1]<i-L+1) { // 제일 앞쪽의 숫자가 범위를 벗어나면
				deq.pollFirst(); // 제일 앞쪽의 숫자 제거
			}
			sb.append(deq.peekFirst()[0]).append(" ");
		}
		System.out.println(sb.toString());
	}
}