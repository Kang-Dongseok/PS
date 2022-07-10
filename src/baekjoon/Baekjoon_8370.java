package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Plane
public class Baekjoon_8370 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n1 = Integer.parseInt(str[0]);
		int k1 = Integer.parseInt(str[1]);
		int n2 = Integer.parseInt(str[2]);
		int k2 = Integer.parseInt(str[3]);
		System.out.println(n1*k1+n2*k2);
	}
}