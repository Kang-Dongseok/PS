package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 세수정렬
public class Baekjoon_2752 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int[] arr = new int[3];
		for(int i=0; i<3; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		for(int i=0; i<3; ++i) {
			sb.append(arr[i]+" ");
		}
		System.out.print(sb.toString());
	}
}