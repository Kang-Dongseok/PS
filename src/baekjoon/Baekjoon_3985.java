package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 롤 케이크
public class Baekjoon_3985 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[L+1]; // 길이L 롤케익 배열, 인덱스0 사용안함
		int[] results = new int[N+1]; // N번 사람이 몇개 조각을 가지고 갔는지 저장하는 배열, 인덱스0 사용안함
		int expMax = 0; // 가장 많이 가지고 갈 것으로 예상되는 사람의 조각 갯수
		int expIdx = 0; // 가장 많이 가지고 갈 것으로 예상되는 사람의 번호
		
		for(int i=1; i<=N; i++) {
			String[] str =  br.readLine().split(" ");
			int P = Integer.parseInt(str[0]);
			int K = Integer.parseInt(str[1]);
			if(expMax < K-P+1) { // 더 많이 들고갈 것 같으면 값을 갱신
				expMax = K-P+1;
				expIdx = i;
			}
			for(int j=P; j<=K; j++) {
				if(arr[j]==0) arr[j] = i; // 누가 들고가지 않았으면 들고감
			}
		}
		for(int i=1; i<=L; i++) { // 몇번이 몇개 들고갔는지 저장
			results[arr[i]]++;
		}
		int resultIdx = 1; // 실제로 제일 많에 들고 간 사람의 번호
		int resultMax = 0;
		for(int i=1; i<=N; i++) {
			if(results[i]>resultMax) {
				resultIdx = i;
				resultMax = results[i];
			}
		}
		System.out.println(expIdx);
		System.out.println(resultIdx);
	}
	
}