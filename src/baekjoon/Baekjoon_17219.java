package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 비밀번호 찾기
public class Baekjoon_17219 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0; i<N; ++i) {
			line = br.readLine().split(" ");
			map.put(line[0], line[1]);
		}
		for(int i=0; i<M; ++i) {
			sb.append(map.get(br.readLine())).append("\n");
		}
		System.out.print(sb.toString());
	}
}