package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수열
public class Baekjoon_2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		int[] arr = new int[N];
		str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		int max = 0;
		int sum = 0;
		for(int i=0; i<K; ++i) {
			sum += arr[i];
		}
		max = sum;
		for(int i=K; i<N; ++i) {
			sum = sum+arr[i]-arr[i-K];
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}