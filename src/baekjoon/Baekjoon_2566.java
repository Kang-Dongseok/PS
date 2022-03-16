package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 최댓값
public class Baekjoon_2566 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int max = 0;
		int r = 0;
		int c = 0;
		for(int i=0; i<9; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<9; ++j) {
				int num = Integer.parseInt(str[j]);
				if(max<num) {
					max=num;
					r=i+1;
					c=j+1;
				}
			}
		}
		System.out.println(max);
		System.out.println(r+" "+c);
	}
}
