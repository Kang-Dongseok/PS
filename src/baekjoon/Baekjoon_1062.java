package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 가르침
/*
 * 조합을 이용하여 단어에 나와있는 알파벳 중 선택한 후
 * 해당 알파벳을 가지고 얼마나 많은 단어들을 읽을 수 있는지 확인하면 된다.
 * 백트랙킹을 적용할 수 있는 부분이 몇군데 있어서, 구현하는데 조금 헷갈렸지만
 * 로직은 그렇게 어렵지 않은 문제였다.
 */
public class Baekjoon_1062 {
	
	static int N,K,result;
	static String[] words;
	static int[] alphabetCnt = new int[26]; // 알파벳 26개 중 21개의 등장 횟수(a,c,i,n,t는 제외)
	static boolean[] learned = new boolean[26]; // 알파벳 26개 'a'~'z'
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		if(K<5) { // a,c,i,n,t 최소 5개 이상의 글자를 알아야하므로
			System.out.println(0);
			return;
		}
		learned[0]=true; learned[2]=true; learned[8]=true; learned[13]=true; learned[19]=true; // a,c,i,n,t는 무조건 사용 
		alphabetCnt[0]++; alphabetCnt[2]++; alphabetCnt[8]++; alphabetCnt[13]++; alphabetCnt[19]++; // a,c,i,n,t는 무조건 1번 이상 등장
		words = new String[N];
		for(int i=0; i<N; ++i) {
			String word = br.readLine();
			words[i] = word;
			for(int j=4,len=word.length(); j<len-4; ++j) {
				alphabetCnt[word.charAt(j)-'a']+=1; // 'anta'와 'tica'사이에 있는 알파엣의 등장 횟수를 증가
			}
		}
		// 등장한 알파벳 수보다 K가 이상이면 모든 단어 가능하므로 N출력 후 종료, 일단 acint도 갯수 증가를 사전에 해줘야함
		int numOfAlphabet = 0;
		for(int i=0; i<26; ++i) {
			if(alphabetCnt[i]>0) numOfAlphabet++; // 알파벳이 등장한 갯수 카운트
		}
		if(K>=numOfAlphabet) { //
			System.out.println(N);
			return;
		}
		result = 0;
		combination(0,0); // 조합
		System.out.println(result);
	}
	
	public static void combination(int n, int idx) { // 현재 n개 알파벳 선택, idx번째 알파벳 부터 확인
		if(n==K-5) { // K-5개의 알파벳을 선택하였으면 (a,c,i,n,t는 제외)
			countWords();
			return;
		}
		if(K-5-n+idx>26) { // 남은 알파벳을 전부 선택해도 K개를 선택할 수 없다면 종료
			return;
		}
		
		for(int i=idx; i<26; ++i) { // 알파벳 26개에 대하여
			if(i==0 || i==2 || i==8 || i==13 || i==19) continue; // a,c,i,n,t이면 선택안함
			if(alphabetCnt[i]>0) { // idx번째 알파벳이 한 번이라도 등장했으면
				learned[i]=true; // 선택
				combination(n+1, i+1); // 다음 알파벳 선택
				learned[i]=false; // 선택 해제
			}
		}
	}
	
	public static void countWords() {
		int count = 0; // 읽을 수 있는 단어의 총 갯수
		for(int i=0; i<N; ++i) {
			if(canRead(words[i])) count++; // 읽을 수 있으면 갯수 증가
		}
		if(result<count) result=count;
	}
	
	public static boolean canRead(String word) {
		int length = word.length();
		for(int i=4; i<length-4; ++i) {
			if(!learned[word.charAt(i)-'a']) return false; // 하나라도 학습하지 않은 알파벳이 있으면 false
		}
		return true;
	}
}