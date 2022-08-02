package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// The Second Largest Integer
public class Baekjoon_20976 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int[] arr = new int[3];
		for(int i=0; i<3; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		System.out.println(arr[1]);
	}
}