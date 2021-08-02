package baekjoon;

import java.util.Scanner;

public class Baekjoon_11653 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = N;
		if(result==1) return;
		for(int i = 2; i <= N; i++) {
			if(result%i==0) {
				while(result%i==0) {
					result /= i;
					System.out.println(i);
				}
			}
			if(result==1) break;
		}
	}

}
