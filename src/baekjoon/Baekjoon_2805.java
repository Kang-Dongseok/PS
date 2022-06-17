package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 나무 자르기
public class Baekjoon_2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[] trees = new int[N];
		str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			trees[i] = Integer.parseInt(str[i]);
		}
		int low = 0;
		int high = 1000000000;
		int answer = 0;
		while(low<=high) {
			int mid = (low+high)/2;
			long sum = 0;
			for(int i=0; i<N; ++i) {
				if(trees[i]>mid) sum += trees[i]-mid;
			}
			if(sum>M) {
				answer = mid;
				low = mid+1;
			}else if(sum==M){
				answer = mid;
				break;
			}else {
				high = mid-1;
			}
		}
		System.out.println(answer);
	}
}