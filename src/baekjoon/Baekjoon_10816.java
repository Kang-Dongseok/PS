package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 숫자 카드 2
public class Baekjoon_10816 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			int num = Integer.parseInt(line[i]);
			map.put(num, map.getOrDefault(num, 0)+1);
		}
		int M = Integer.parseInt(br.readLine());
		line = br.readLine().split(" ");
		for(int i=0; i<M; ++i) {
			int num = Integer.parseInt(line[i]);
			if(map.containsKey(num)) {
				sb.append(map.get(num)).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}