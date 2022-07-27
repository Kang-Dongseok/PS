package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Piece of Cake!
public class Baekjoon_17874 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int h = Integer.parseInt(str[1]);
		int v = Integer.parseInt(str[2]);
		h = Math.max(h, n-h);
		v = Math.max(v, n-v);
		System.out.println(h*v*4);
	}	
}