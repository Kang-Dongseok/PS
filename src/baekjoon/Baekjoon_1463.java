package baekjoon;

import java.util.Scanner;

public class Baekjoon_1463 {
	미해결
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int answer = 0;
		if(x%3==1) {
			x--;
			answer++;
		} else if(x%3==0) {
			x /= 3;
			answer++;
		} else if(x%2==0) {
			x /= 2;
			answer++;
		} else {
			
		}
		
	}

}
