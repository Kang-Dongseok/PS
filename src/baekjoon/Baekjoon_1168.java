package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 요세푸스 문제2
//시간 or 메모리 초과
public class Baekjoon_1168 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		sb.append("<");
		int idx = 0;
		int cnt = 0;
		int selected = 0;
		while(selected<N) {
			if(arr[idx]!=0) {
				cnt++;
			}
			if(cnt==K) {
				sb.append(arr[idx]).append(", ");
				arr[idx] = 0;
				cnt = 0;
				selected++;
			}
			idx = (idx+1) % N;
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}
}
