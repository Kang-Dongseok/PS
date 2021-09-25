package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 찾기
/*
 * KMP 알고리즘(Knuth–Morris–Pratt Algorithm) 
 * 시간복잡도: O(N+M)
 */
public class Baekjoon_1786 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		
		int tLength = text.length, pLength = pattern.length;
		
		// 부분일치테이블 만들기 : KMP의 아이디어를 똑같이 적용, W에서 W를 찾는 듯한 행위를 해서...
		int[] pi = new int[pLength];
	    for(int i=1, j=0; i<pLength; i++){// i:접미사 포인터(i=1부터 시작: 우리는 부분일치테이블을 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.)
	    								  // j:접두사 포인터
	        while(j > 0 && pattern[i] != pattern[j]) {
	        	j = pi[j-1];
	        }
	        // pattern[i] != pattern[j]
	        if(pattern[i] == pattern[j]) pi[i] = ++j;
	        // j == 0
//		        else pi[i] = 0; // 어차피 0으로 초기화
	    }
		
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		// i : 텍스트 포인터 , j: 패턴 포인터 
		for(int i=0,j=0; i<tLength; ++i) { 
			
			while(j>0 && text[i] != pattern[j]) j = pi[j-1]; 
			
			if(text[i] == pattern[j]) { //두 글자 일치
				if(j == pLength-1) { // j가 패턴의 마지막 인덱스라면 
					cnt++; // 카운트 증가
					list.add((i+1)-pLength); 
					j=pi[j]; 
				}else { // 패턴 일치 중간 과정(패턴 앞쪽 일치하며 진행중인 상황)
					j++;
				}
			}
		}
		
		System.out.println(cnt);
		if(cnt>0) {
			for(int n : list) {
				System.out.print(n+1+" ");
			}
		}
	}	
}