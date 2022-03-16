package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 괄호 추가하기
/*
 * 숫자를 앞에서부터 1개 혹은 2개먼저 연산한 결과를 차근차근 연산하면 된다.
 * 생각보다 문제가 너무 간단해서 골드3이라고 하기에는 쉬운 편이었다.
 * dfs 방식을 사용하여 해결하였다.
 */
public class Baekjoon_16637 {

	static int N,numOfNumber,result;
	static int[] calcArr;
	static String sentence;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N은 무조건 홀수
		sentence = br.readLine();
		numOfNumber = N/2+1; // 숫자의 갯수
		calcArr = new int[N/2]; // index번째 연산자 앞뒤의 수를 연산한 결과를 저장, 연산자 갯수만큼 생성
		for(int i=1,length=sentence.length(); i<length; i+=2) { // 연산자를 기준으로 앞뒤 숫자를 연산하여 저장
			calcArr[i/2] = operate(sentence.charAt(i-1)-'0',sentence.charAt(i),sentence.charAt(i+1)-'0');
		}
		
		result=Integer.MIN_VALUE; // 최댓값 초기화
		calc(1,sentence.charAt(0)-'0'); // 시작 시 첫 번째 수는 무조건 선택, dfs
		System.out.println(result);
	}
	
	public static void calc(int n, int sum) { // 앞에서 n개의 숫자를 연산한 값이 sum
		if(n==numOfNumber) { // 정수 갯수만큼 다 계산하면
			if(result<sum) result=sum; // 최댓값 갱신
			return;
		}
		
		calc(n+1,operate(sum, sentence.charAt(n*2-1), sentence.charAt(n*2)-'0')); // 바로 다음에 오는 숫자 하나를 연산
		// 바로 다음에 숫자가 2개이상 남아 있으면, 2개 숫자를 먼저 연산 후 앞의 결과와 연산
		if(n+2<=numOfNumber) calc(n+2,operate(sum, sentence.charAt(n*2-1), calcArr[n]));
	}
	
	public static int operate(int n1, char op, int n2) { // n1과 n2를 op연산자로 계산하여 리턴
		if(op=='+') return n1+n2; // 덧셈
		else if(op=='-') return n1-n2; // 뺄셈
		else return n1*n2; // 곱셈
	}
}