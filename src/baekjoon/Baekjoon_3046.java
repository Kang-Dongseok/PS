package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// R2
public class Baekjoon_3046 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		System.out.println(2*Integer.parseInt(str[1])-Integer.parseInt(str[0]));
	}
}