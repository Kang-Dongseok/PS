package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Football Scoring
public class Baekjoon_24736 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int T = Integer.parseInt(str[0]);
		int F = Integer.parseInt(str[1]);
		int S = Integer.parseInt(str[2]);
		int P = Integer.parseInt(str[3]);
		int C = Integer.parseInt(str[4]);
		int a = 6*T+3*F+S*2+P+C*2;
		str = br.readLine().split(" ");
		T = Integer.parseInt(str[0]);
		F = Integer.parseInt(str[1]);
		S = Integer.parseInt(str[2]);
		P = Integer.parseInt(str[3]);
		C = Integer.parseInt(str[4]);
		int b = 6*T+3*F+S*2+P+C*2;
		System.out.println(a+" "+b);
	}
}