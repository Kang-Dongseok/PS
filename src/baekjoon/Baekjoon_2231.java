package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2231 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int n_length = isLength(n);  // n의 자릿수
		int answer = 0;
		for(int i=n-1;i>=n-(9*n_length);i--) {
			if(func(i)==n) {
				answer = i;
			}
		}
		System.out.println(answer);
	}
	public static int isLength(int n) {
		int length = 1;
		while(n/10>0) {  // n의 자릿수 구하기
			n /= 10;
			length++;
		}
		return length;
	}
	public static int func(int n) {  // 자릿수의 합을 다 더한 수
		int sum = n;
		while(n/10>0) {
			sum += n%10;
			n /= 10;
		}
		sum += n;
		return sum;
	}
}
