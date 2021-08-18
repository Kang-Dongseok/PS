package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int[] arr = new int[8];
		for(int i=0; i<8; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		if(arr[0]==1) {
			for(int i=1; i<8; ++i) {
				if(arr[i]!=i+1) {
					System.out.println("mixed");
					return;
				}
			}
			System.out.println("ascending");
		}else {
			for(int i=0; i<8; ++i) {
				if(arr[i]!=8-i) {
					System.out.println("mixed");
					return;
				}
			}
			System.out.println("descending");
		}
	}
}