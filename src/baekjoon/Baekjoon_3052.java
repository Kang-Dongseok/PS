package baekjoon;

import java.util.Scanner;

public class Baekjoon_3052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int total = 0;
		int[] arr = new int[42];
		for(int i = 0; i< 10; i++) {
			int n = sc.nextInt();
			arr[n %= 42]=1;
		}
		for(int num : arr) {
			total += num;
		}
		System.out.println(total);
	}

}
