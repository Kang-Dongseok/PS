package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 단어 수학
/*
 * 알파벳 각각의 계수를 다 구한 후 가장 높은 계수부터 차례대로 9부터 하나씩 감소하여 대입한다.
 * 시간 복잡도: O(N), N에 비례하는게 for문 하나 정도...?
 */
public class Baekjoon_1339 {

	static int[] arr = new int[26]; // 알파벳 A 부터 Z 까지의 계수를 담을 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 배열에 알파벳들의 계수를 저장
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int size = str.length();
			for(int j=0; j<size; j++) {
				arr[str.charAt(j)-'A'] += (int)Math.pow(10, size-j-1);
			}
		}
		Arrays.sort(arr); // 정렬하여 끝에서부터 가장 높은 계수를 찾는다.
		int sum = 0;
		int num = 9;
		for(int i=arr.length-1;i>=0;i--) { // 계수 높은 수 부터 차례대로 9부터 하나씩 감소하며 곱한다.
			if(arr[i]!=0) { // 계수가 존재하면
				sum += num-- * arr[i];
			}
		}
		System.out.println(sum);
	}
}