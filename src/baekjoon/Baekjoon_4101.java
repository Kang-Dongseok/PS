package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 크냐?
public class Baekjoon_4101 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] str = br.readLine().split(" ");
			int n1 = Integer.parseInt(str[0]);
			int n2 = Integer.parseInt(str[1]);
			if(n1==0 && n2==0) break;
			if(n1>n2) System.out.println("Yes");
			else System.out.println("No");
		}
	}
}