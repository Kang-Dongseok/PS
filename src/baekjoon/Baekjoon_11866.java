package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 요세푸스 문제0
public class Baekjoon_11866 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		sb.append("<");
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=N; ++i) {
			q.offer(i);
		}
		while(!q.isEmpty()) {
			for(int i=0; i<K-1; ++i) {
				q.offer(q.poll());
			}
			sb.append(q.poll()+", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb.toString());
	}
}
