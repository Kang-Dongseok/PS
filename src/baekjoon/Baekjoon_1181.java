package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 단어 정렬
public class Baekjoon_1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		for(int i=0; i<N; ++i) {
			arr[i] = br.readLine();
		}
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) { // 길이가 같으면 사전순
					return o1.compareTo(o2);
				}else { // 길이가 다르면 길이순
					return o1.length()-o2.length();
				}
			}
		});
		
		System.out.println(arr[0]);
		
		for(int i=1; i<N; ++i) {
			if(!arr[i].equals(arr[i-1])) System.out.println(arr[i]);
		}
	}
}