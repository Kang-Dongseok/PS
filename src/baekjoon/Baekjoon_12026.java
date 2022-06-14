package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// BOJ 거리
public class Baekjoon_12026 {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	if(N==1) { // 길이가 1이면 0
    		System.out.println(0);
    		return;
    	}
    	String str = br.readLine();
    	int[] dp = new int[N];
    	for(int i=1; i<N; ++i) {
    		if(str.charAt(i)=='O') {
    			dp[i] = i*i; // 첫B다음 O는 전부 초기화
    		}
    	}
    	for(int i=1; i<N-1; ++i) {
    		if(dp[i]==0) continue; // 해당위치로 오는 경로가 없으면 무시
    		char ch1 = str.charAt(i);
    		for(int j=i+1; j<N; ++j) {
    			char ch2 = str.charAt(j);
    			if((ch1=='B' && ch2=='O') || (ch1=='O' && ch2=='J') || (ch1=='J' && ch2=='B')) { // 다음 알파벳 순서가 맞으면
    				if(dp[j]==0) { // 아직 경로가 없으면 무조건 대입
    					dp[j] = dp[i]+(j-i)*(j-i);
    				}else { // 기존 경로가 있으면 최솟값 갱신
    					dp[j] = Math.min(dp[j], dp[i]+(j-i)*(j-i));
    				}
    			}
    		}
    	}
    	if(dp[N-1]==0) {
    		System.out.println(-1);
    	}else {
    		System.out.println(dp[N-1]);
    	}
    }
}