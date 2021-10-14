package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 파일 합치기
/*
 * 점화식을 찾는 것이 생각보다 어려웠다.
 * dp[i][j]를 i번쨰부터 j번쨰까지를 합친 최소의 비용이라고 하면, 점화식은 다음과 같다.
 * dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+sum[j]-sum[i-1])
 * 시간복잡도 : O(N^3)
 */
public class Baekjoon_11066 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
        	int K = Integer.parseInt(br.readLine());
        	int[] chapter = new int[K+1];
        	int[] sum = new int[K+1]; // 1번쨰 chapter부터 i번째 챕터까지의 합
        	int[][] dp = new int[K+1][K+1];
        	String[] str = br.readLine().split(" ");
        	for(int i=1; i<K+1; ++i) {
        		chapter[i] = Integer.parseInt(str[i-1]);
        	}
        	for(int i=1; i<K+1; ++i) {
        		sum[i] = sum[i-1]+chapter[i];
        	}
        	
        	for(int j=1; j<=K; ++j) {
        		for(int i=j-1; i>0; --i) {
        			dp[i][j] = Integer.MAX_VALUE; // 최솟값을 찾기 위해 초기화
        			for(int k=i; k<j; ++k) {
        				dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]); // 점화식
        			}
        			dp[i][j] += sum[j] - sum[i-1];
        		}
        	}
        	System.out.println(dp[1][K]);
        	for(int[] ar : dp) {
        		for(int a : ar) {
        			System.out.print(a+"\t");
        		}
        		System.out.println();
        	}
        }

    }
}