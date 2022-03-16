package baekjoon;

import java.util.Scanner;

public class Baekjoon_8958 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		int[] arr = new int[n];
		for(int i = 0; i< n; i++) {
			int point = 0;
			int total = 0;
			String str = sc.nextLine();
			for(int j= 0; j< str.length(); j++) {
				if(str.charAt(j)=='X') {
					point = 0;
				}else {
					total += ++point;
				}
			}
			arr[i] = total;
		}
		for(int num : arr) {
			System.out.println(num);
		}
	}

}
