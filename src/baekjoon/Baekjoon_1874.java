package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 스택 수열
public class Baekjoon_1874 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		int num = 1; // 들어갈 숫자
		for(int i=0; i<n; ++i) {
			int cur = Integer.parseInt(br.readLine());
			while(cur>=num) { // 해당숫자까지 채우면서 +
				stack.push(num++);
				sb.append("+").append("\n");
			}
			if(stack.peek()==cur) { // 꺼낼숫자가 해당숫자와 일치하면 그대로 꺼내기
				stack.pop();
				sb.append("-").append("\n");
			}else { // 일치하지 않으면 수열이 다르므로 종료
				System.out.println("NO");
				return;
			}
		}
		System.out.println(sb.toString());
	}
}