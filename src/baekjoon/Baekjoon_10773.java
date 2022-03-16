package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


// 제로
public class Baekjoon_10773 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int result = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<K; ++i) {
			int num = Integer.parseInt(br.readLine());
			if(num!=0) stack.push(num);
			else stack.pop();
		}
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		System.out.println(result);
	}
}
