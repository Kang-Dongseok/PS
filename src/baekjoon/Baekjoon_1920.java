package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_1920 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		Arrays.sort(arr);  // Arrays.binarySearch(탐색배열,탐색값) 을 사용하려면 오름차순 정렬(sort())가 필수적으로 선행!
		int M = sc.nextInt();
		for(int i=0;i<M;i++) {
			if(Arrays.binarySearch(arr, sc.nextInt()) >= 0) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
		
	}
}
