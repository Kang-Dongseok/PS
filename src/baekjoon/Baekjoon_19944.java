package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 뉴비의 기준은 뭘까?
public class Baekjoon_19944 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		if(M<3) {
			System.out.println("NEWBIE!");
		}else if(N>=M) {
			System.out.println("OLDBIE!");
		}else {
			System.out.println("TLE!");
		}
	}
}