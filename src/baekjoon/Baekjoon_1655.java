package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 가운데를 말해요
/*
 * 이분탐색을 통해 새로운 숫자가 어디에 위치할지 빠르게 찾은 후
 * 계속해서 중간값을 출력하면 된다.
 */
public class Baekjoon_1655 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(Integer.parseInt(br.readLine())); // 1개는 넣기
		sb.append(list.get(0)).append("\n"); // 1개만 있을 때 숫자 말하기
		for(int i=1; i<N; ++i) { // 나머지 N-1개 넣기
			int n = Integer.parseInt(br.readLine());
			int low = 0;
			int high = list.size()-1;
			int mid = 0;
			while(low<=high) {
				mid = (low+high)/2;
				if(list.get(mid)>n) {
					high = mid-1;
				}else if(list.get(mid)<n) {
					low = mid+1;
				}else { // 같은 값을 찾으면
					break;
				}
			}
			if(list.get(mid)>=n) { // 새로운 수가 mid보다 같거나 작으면
				list.add(mid,n); // 그자리에 집어넣기
			}else { // mid보다 크면
				list.add(mid+1,n); // 한 칸 다음에 집어넣기
			}
			sb.append(list.get((i)/2)).append("\n");
		}
		System.out.println(sb.toString());
	}
}