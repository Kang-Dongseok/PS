package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// 미해결
// 구슬 찾기
public class Baekjoon_2617 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		ArrayList<Set<Integer>> before = new ArrayList<Set<Integer>>();
		ArrayList<Set<Integer>> after = new ArrayList<Set<Integer>>();
		for(int i=0; i<N; ++i) {
			before.add(new HashSet<Integer>());
			after.add(new HashSet<Integer>());
		}
		for(int i=0; i<M; ++i) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0])-1;
			int b = Integer.parseInt(str[1])-1;
			before.get(a).add(b);
			before.get(a).addAll(before.get(b));
			after.get(b).add(a);
			after.get(b).addAll(after.get(a));
		}
		int answer = 0;
		for(int i=0; i<N; ++i) {
//			System.out.println(before.get(i).size());
//			System.out.println(after.get(i).size());
			if(before.get(i).size()>=(N+1)/2 || after.get(i).size()>=(N+1)/2) {
				answer++;
			}
		}
		System.out.println(answer);
		
		
		
		
	}
}