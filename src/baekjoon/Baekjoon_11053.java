package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 가장 긴 증가하는 부분 수열
public class Baekjoon_11053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] LIS = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		int size = 0;
		for(int i=0; i<N; ++i) {
			int temp = Arrays.binarySearch(LIS, 0, size, arr[i]);
			if(temp<0) {
				temp = Math.abs(temp)-1;
			}
			LIS[temp] = arr[i];
			if(temp==size) ++size;
		}
		System.out.println(size);
	}
}