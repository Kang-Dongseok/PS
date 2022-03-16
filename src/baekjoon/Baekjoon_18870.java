package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 좌표 압축
/*
 * 값이 같을 떄 결과가 같으면 Map<Key, Value>을 떠올리자!
 * 시간 복잡도 : NlogN (정렬이 NlogN 나머지는 N이므로)
 */
public class Baekjoon_18870 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 배열 생성 후 대입
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 배열 정렬
		int[] sortedArr = arr.clone();  // 배열 복사
		Arrays.sort(sortedArr);
		
		int size = sortedArr.length;
		int flag = sortedArr[size-1];
		int cnt = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if(flag==sortedArr[0]) { // 모든 값이 같으면
			map.put(flag,cnt++);
		}else {
			for(int i=0;i<size;i++) {
				if(flag!=sortedArr[i]) {
					flag = sortedArr[i];
					map.put(flag,cnt++);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0,len=arr.length; i<len;i++) {
			if(i==len-1) {
				sb.append(map.get(arr[i]));
				break;
			}
			sb.append(map.get(arr[i])+" ");
		}
		System.out.print(sb.toString());
	}

}
