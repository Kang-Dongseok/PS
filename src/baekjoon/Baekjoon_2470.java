package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 두 용액
/*
 * 하나를 고정하고 나머지 하나를 0에 가깝게 이분탐색으로 찾는다.
 * 이 때, 첫번째 선택한 수와 두번째 이분탐색하면서 찾는 수가 같이 않아야 하는것에 주의한다.
 */
public class Baekjoon_2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr); // 오름차순 정렬
		if(arr[0] > 0) { // 전부 양수
			System.out.println(arr[0]+" "+arr[1]);
			return;
		}else if(arr[N-1]<0) { // 전부 음수
			System.out.println(arr[N-2]+" "+arr[N-1]);
			return;
		}
		int n1=0,n2=0; // 비교 숫자
		int ans1=0,ans2=0; // 답인 숫자
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; ++i) {
			n1 = arr[i];
			int low = 0;
			int high = N-1;
			while(low<=high) {
				int mid = (low+high)/2;
				n2 = arr[mid];
				int sum = n1+n2;
				if(sum==0) { // 합이 0이면
					if(n1<n2) {
						System.out.println(n1+" "+n2);
					}else {
						System.out.println(n2+" "+n1);
					}
					return;
				}else { // 0아니면
					if(n1!=n2 && Math.abs(min)>Math.abs(sum)) { // 같은 수를 선택하지 않고, 절댓값이 작으면 갱신
						min = sum;
						ans1=n1;
						ans2=n2;
					}
					if(sum>0) {
						high=mid-1;
					}else {
						low=mid+1;
					}
				}
			}
		}
		if(ans1<ans2) {
			System.out.println(ans1+" "+ans2);
		}else {
			System.out.println(ans2+" "+ans1);
		}
	}
}