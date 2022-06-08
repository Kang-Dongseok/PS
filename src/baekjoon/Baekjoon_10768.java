package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 특별한 날
public class Baekjoon_10768 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		if(M<2) {
			System.out.println("Before");
		}else if(M>2) {
			System.out.println("After");
		}else {
			if(D<18) {
				System.out.println("Before");
			}else if(D>18) {
				System.out.println("After");
			}else {
				System.out.println("Special");
			}
		}
	}
}