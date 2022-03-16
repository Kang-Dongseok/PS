package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
// 오큰수
public class Baekjoon_17298 {

	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().trim().split(" ");
		Stack<Integer> stack = new Stack<Integer>();
		int[] answer = new int[N]; // 정답 배열
		for(int i=N-1; i>=0; --i) {
			int num = Integer.parseInt(str[i]);
			while(!stack.isEmpty()) {
				if(stack.peek()<=num) stack.pop();
				else break;
			}
			if(!stack.isEmpty()) answer[i] = stack.peek();
			else answer[i] = -1; // 다음에 오는 큰 수가 없으면
			stack.push(num);
		}
		for(int i=0; i<N; ++i) {
			sb.append(answer[i]+" ");
		}
		System.out.println(sb.toString());
	}
}