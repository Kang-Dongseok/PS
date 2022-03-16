package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
/*
 * 시간 복잡도 : N (스택 for문 한 번 돌면서 각 토큰마다 상수만큼(?) 움직이기 떄문에)
 */
// 후위 표기식
public class Baekjoon_1918 {

	static ArrayList<Character> arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		arr = new ArrayList<Character>();
		String str = br.readLine();
		int size = str.length();
		for(int i=0; i<size; i++) {
			arr.add(str.charAt(i));
		}
		// 전체 괄호로 감싸기
		arr.add(0,'(');
		arr.add(')');
//		System.out.println(arr.toString());
		// 중위 표기식 -> 후위 표기식 전환
		Stack<Character> stack = new Stack<Character>();
		for(int i=0,len=arr.size();i<len;i++) {
			char ch = arr.get(i);
			if(ch=='(') {
				stack.push(ch);
			} else if(ch==')') {
				while(stack.peek()!='(') {
					sb.append(stack.pop());
				}
				if(stack.peek()=='(') stack.pop();
			} else if(ch=='*'||ch=='/') {
				while(stack.peek()=='*' || stack.peek()=='/') {
					sb.append(stack.pop());
				}
				stack.push(ch);
			} else if(ch=='+'||ch=='-') {
				while(stack.peek()=='*' || stack.peek()=='/' || stack.peek()=='+' || stack.peek()=='-') {
					sb.append(stack.pop());
				}
				stack.push(ch);
			} else {
				sb.append(ch);
			}
		}
		
		System.out.println(sb);
	}
	
}