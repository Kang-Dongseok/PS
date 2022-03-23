package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 덱
public class Baekjoon_10866 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> deq = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] line;
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			String order = line[0];
			int num=0;
			if(line.length==2) num = Integer.parseInt(line[1]);
			if(order.equals("push_front")) {
				deq.offerFirst(num);
			}else if(order.equals("push_back")) {
				deq.offerLast(num);
			}else if(order.equals("pop_front")) {
				if(deq.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(deq.pollFirst()).append("\n");
				}
			}else if(order.equals("pop_back")) {
				if(deq.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(deq.pollLast()).append("\n");
				}
			}else if(order.equals("size")) {
				sb.append(deq.size()).append("\n");
			}else if(order.equals("empty")) {
				if(deq.isEmpty()) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else if(order.equals("front")) {
				if(deq.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(deq.peekFirst()).append("\n");
				}
			}else if(order.equals("back")) {
				if(deq.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(deq.peekLast()).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}