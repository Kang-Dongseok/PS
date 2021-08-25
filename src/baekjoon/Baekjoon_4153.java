package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 직각삼각형
public class Baekjoon_4153 {
	
	static String[] line;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			line = br.readLine().split(" ");
			int n1 = Integer.parseInt(line[0]);
			if(n1==0) break;
			int n2 = Integer.parseInt(line[1]);
			int n3 = Integer.parseInt(line[2]);
			if(n1*n1 == n2*n2+n3*n3 || n2*n2 == n1*n1+n3*n3 || n3*n3 == n1*n1+n2*n2) System.out.println("right");
			else System.out.println("wrong");
		}
		
	}
}
