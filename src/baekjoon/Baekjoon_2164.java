package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class Baekjoon_2164 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// Computational Thinking으로 풀자면
		// 풀이1: ArrayList 이용
		/*
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i=1;i<=N;i++) {
			arr.add(i);
		}
		boolean isOut = true;  // 문제에서 주어진대로 삭제 먼저 시작
		while(arr.size()!=1) {
			for(int i=0,size=arr.size();i<size;i++) {
				if(isOut) {
					arr.remove(i);
					size--;
					i--;
				}
				isOut = !isOut;  // true <-> false toggle
			}
		}
		System.out.println(arr.get(0));
		*/
		
		// 풀이2: 배열 이용
		/*
		int[] arr2 = new int[N*2];
		int index = N;
		boolean isOut2 = true;
		for(int i=0;i<N;i++) {
			arr2[i] = i+1;
		}
		for(int i=0;i<N*2;i++) {
			if(arr2[i]==0) {
				System.out.println(arr2[i-1]);
			}
			if(!isOut2) {
				arr2[index++] = arr2[i];
			}
			isOut2 = !isOut2;
		}
		*/
		
//		수학적으로 풀면
//		1,2,/2,4,/2,4,6,8,/2,4,6,8,10,12,14,16,/2 순서대로 정답 규칙
		
		// 풀이3: 수학적 규칙 이용
		if(N==1) {
			System.out.println(1);
			return;
		}
		int num=1;
		while(num*2<N) {
			num*=2;
		}
		System.out.println((N-num)*2);
	}

}
