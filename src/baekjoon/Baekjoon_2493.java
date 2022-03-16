package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑
public class Baekjoon_2493 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] result = new int[n];
		Stack<int[]> stack = new Stack<int[]>();
		int[] arr;
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<n; i++) {
			int next = Integer.parseInt(st.nextToken());
			arr = new int[2]; // {높이, 인덱스}
			arr[0] = next;
			arr[1] = i;
			if(!stack.isEmpty()) {
				if(next<stack.peek()[0]) {  // 직전보다 작은 값
					result[i] = stack.peek()[1]+1;  // 인덱스 저장하고 push
					stack.push(arr);
				}else {  // 직전보다 큰 값
					while(!stack.isEmpty() && next>stack.peek()[0]) {  // 더 큰 값 나올떄까지 pop
							stack.pop();
					}
					if(stack.isEmpty()) {  // 더 큰 값 없으면(즉, 최대 값이면)
						result[i] = 0;
						stack.push(arr);
					} else {  // 더 큰 값 찾으면
						result[i] = stack.peek()[1]+1;
						stack.push(arr);
					}
				}
			} else {
				stack.push(arr);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i : result) {
			sb.append(i+" ");
		}
		System.out.print(sb);
		
	}

}
