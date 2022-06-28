package baekjoon;

import java.io.*;

// 팬들에게 둘러싸인 홍준
public class Baekjoon_14581 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		System.out.println(":fan::fan::fan:");
		System.out.println(":fan::"+name+"::fan:");
		System.out.println(":fan::fan::fan:");
	}
}