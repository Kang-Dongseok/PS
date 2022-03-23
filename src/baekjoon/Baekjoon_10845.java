package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 큐
public class Baekjoon_10845 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> q = new LinkedList<Integer>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] line;
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			String order = line[0];
			if(order.equals("push")) {
				int num = Integer.parseInt(line[1]);
				q.offer(num);
			}else if(order.equals("pop")) {
				if(q.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(q.poll()).append("\n");
				}
			}else if(order.equals("size")) {
				sb.append(q.size()).append("\n");
			}else if(order.equals("empty")) {
				if(q.isEmpty()) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else if(order.equals("front")) {
				if(q.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(q.peek()).append("\n");
				}
			}else if(order.equals("back")) {
				if(q.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(q.peekLast()).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}