package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// The Nearest Value
public class Baekjoon_18414 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int a = Integer.parseInt(str[0]);
		int b = Integer.parseInt(str[1]);
		int c = Integer.parseInt(str[2]);
		if(a<=b) System.out.println(b);
		else if(a>=c) System.out.println(c);
		else System.out.println(a);
	}	
}