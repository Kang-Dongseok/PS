package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 합이 0인 네 정수
public class Baekjoon_7453 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int a[] = new int[n];
		int b[] = new int[n];
		int c[] = new int[n];
		int d[] = new int[n];
		String[] str;
		for(int i=0; i<n; ++i) {
			str = br.readLine().split(" ");
			for(int j=0; j<4; ++j) {
				a[i] = Integer.parseInt(str[0]);
				b[i] = Integer.parseInt(str[1]);
				c[i] = Integer.parseInt(str[2]);
				d[i] = Integer.parseInt(str[3]);
			}
		}
		int N = n*n;
		int[] ab = new int[N];
		int[] cd = new int[N];
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				ab[i*n+j] = a[i]+b[j];
				cd[i*n+j] = c[i]+d[j];
			}
		}
		Arrays.sort(ab);
		Arrays.sort(cd);
		long ans = 0; // 정답
		int idx1 = 0;
		int idx2 = N-1;
		while(idx1<N && idx2>=0) {
			int num1 = ab[idx1];
			int num2 = cd[idx2];
			int sum = num1+num2;
			if(sum==0) {
				int cnt1 = 1;
				while(idx1<N-1 && ab[idx1]==ab[idx1+1]) { // 같은 숫자 있으면
					idx1++;
					cnt1++;
				}
				int cnt2 = 1;
				while(idx2>0 && cd[idx2]==cd[idx2-1]) { // 같은 숫자 있으면
					idx2--;
					cnt2++;
				}
				ans += (long)cnt1*cnt2;
			}
			if(sum<0) {
				idx1++; // 증가
			}else {
				idx2--; // 감소
			}
		}
		System.out.println(ans);
	}
}