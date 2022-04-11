package baekjoon;

import java.io.*;

// 더하기
public class Baekjoon_10822 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(",");
		int sum = 0;
		for(int i=0,len=str.length; i<len; ++i) {
			sum += Integer.parseInt(str[i]);
		}
		System.out.println(sum);
	}
}