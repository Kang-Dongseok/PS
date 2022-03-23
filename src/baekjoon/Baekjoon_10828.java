package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 스택
public class Baekjoon_10828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] line;
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			String order = line[0];
			if(order.equals("push")) {
				int num = Integer.parseInt(line[1]);
				stack.add(num);
			}else if(order.equals("pop")) {
				if(stack.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(stack.pop()).append("\n");
				}
			}else if(order.equals("size")) {
				sb.append(stack.size()).append("\n");
			}else if(order.equals("empty")) {
				if(stack.isEmpty()) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else if(order.equals("top")) {
				if(stack.isEmpty()) {
					sb.append(-1).append("\n");
				}else {
					sb.append(stack.peek()).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}