package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑
public class Baekjoon_2493 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] result = new int[n];
		Stack<Map<String, Integer>> stack = new Stack<Map<String,Integer>>();
		Map<String, Integer> map;
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<n; i++) {
			int next = Integer.parseInt(st.nextToken());
			map = new HashMap<String, Integer>();
			map.put("height", next);
			map.put("index", i);
			if(!stack.isEmpty()) {
				if(next<stack.peek().get("height")) {  // 직전보다 작은 값
					result[i] = stack.peek().get("index")+1;  // 인덱스 저장하고 push
					stack.push(map);
				}else {  // 직전보다 큰 값
					while(!stack.isEmpty() && next>stack.peek().get("height")) {  // 더 큰 값 나올떄까지 pop
							stack.pop();
					}
					if(stack.isEmpty()) {  // 더 큰 값 없으면(즉, 최대 값이면)
						result[i] = 0;
						stack.push(map);
					} else {  // 더 큰 값 찾으면
						result[i] = stack.peek().get("index")+1;
						stack.push(map);
					}
				}
			} else {
				stack.push(map);
			}
		}
			
		for(int i : result) {
			System.out.print(i+" ");
		}
		
	}

}
