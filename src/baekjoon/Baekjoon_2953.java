package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 나는 요리사다
public class Baekjoon_2953 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int num = 0;
		int sum = 0;
		for(int i=1; i<=5; i++) {
			str = br.readLine().split(" ");
			int temp = 0;
			for(int j=0; j<4; j++) {
				temp += Integer.parseInt(str[j]);
			}
			if(sum<temp) {
				num = i;
				sum = temp;
			}
		}
		System.out.println(num+" "+sum);
	}
}