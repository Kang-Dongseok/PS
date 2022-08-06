package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Winning Score
public class Baekjoon_17009 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = 0;
		int b = 0;
		a += Integer.parseInt(br.readLine())*3;
		a += Integer.parseInt(br.readLine())*2;
		a += Integer.parseInt(br.readLine());
		b += Integer.parseInt(br.readLine())*3;
		b += Integer.parseInt(br.readLine())*2;
		b += Integer.parseInt(br.readLine());
		if(a>b) System.out.println("A");
		else if(a<b) System.out.println("B");
		else System.out.println("T");
	}
}
