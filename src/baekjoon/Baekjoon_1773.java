package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 폭죽쇼
public class Baekjoon_1773 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0; i<N; ++i) {
			int n = Integer.parseInt(br.readLine());
			if(n==1) {
				System.out.println(C);
				return;
			}
			int num = n;
			while(num<=C) {
				set.add(num);
				num += n;
			}
		}
		System.out.println(set.size());
	}
}