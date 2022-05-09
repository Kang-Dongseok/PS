package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 네 번째 점
public class Baekjoon_3009 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> setX = new HashSet<Integer>();
		Set<Integer> setY = new HashSet<Integer>();
		for(int i=0; i<3; ++i) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			if(setX.contains(x)) {
				setX.remove(x);
			}else {
				setX.add(x);
			}
			if(setY.contains(y)) {
				setY.remove(y);
			}else {
				setY.add(y);
			}
		}
		int ansX = 0;
		int ansY = 0;
		for(int x : setX) {
			ansX = x;
		}
		for(int y : setY) {
			ansY = y;
		}
		System.out.println(ansX+" "+ansY);
	}
}