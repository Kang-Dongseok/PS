package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백설 공주와 일곱 난쟁이
/*
 * 9개 숫자 더한 후 100을 뺀 숫자가 난쟁이 2명의 숫자 합이랑 같도록 조합을 구한다.
 */
public class Baekjoon_3040 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[9];
		int sum = 0;
		for(int i=0;i<9;i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
			sum += num;
		}
		sum -= 100; // 가짜 난쟁이 2명 숫자의 합
	LOOP:for(int i=0;i<8;i++) {
			for(int j=0;j<9;j++) {
				if(arr[i]+arr[j]==sum) {
					arr[i]=0; arr[j]=0;
					break LOOP;
				}
			}
		}
		for(int num : arr) {
			if(num!=0) sb.append(num).append("\n");
		}
		System.out.print(sb);
	}
	
}