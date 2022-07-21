package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Dog Treats
public class Baekjoon_19602 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int L = Integer.parseInt(br.readLine());
		if(S+2*M+3*L>9) System.out.println("happy");
		else System.out.println("sad");
	}
}