package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Rats
public class Baekjoon_18301 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n1 = Integer.parseInt(str[0]);
		int n2 = Integer.parseInt(str[1]);
		int n12 = Integer.parseInt(str[2]);
		System.out.println((n1+1)*(n2+1)/(n12+1)-1);
	}
}