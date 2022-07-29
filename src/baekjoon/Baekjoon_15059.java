package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Hard choice
public class Baekjoon_15059 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int a1 = Integer.parseInt(str[0]);
		int b1 = Integer.parseInt(str[1]);
		int c1 = Integer.parseInt(str[2]);
		str = br.readLine().split(" ");
		int a2 = Integer.parseInt(str[0]);
		int b2 = Integer.parseInt(str[1]);
		int c2 = Integer.parseInt(str[2]);
		int sum = 0;
		if(a2>a1) sum += a2-a1;
		if(b2>b1) sum += b2-b1;
		if(c2>c1) sum += c2-c1;
		System.out.println(sum);
	}
}