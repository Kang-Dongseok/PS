package baekjoon;

import java.util.Scanner;

public class Baekjoon_2941 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int length = str.length();
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i)=='-') {
				length--;
			} else if(str.charAt(i)=='j') {
				if(i>0 && (str.charAt(i-1)=='l'||str.charAt(i-1)=='n')) {
					length--;
				}
			} else if(str.charAt(i)=='=') {
				length--;
				if(i>1 && str.charAt(i-2)=='d' && str.charAt(i-1)=='z') {
					length--;
				}
			}
		}
		System.out.println(length);
	}
}
