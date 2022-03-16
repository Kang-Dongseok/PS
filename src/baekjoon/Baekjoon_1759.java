package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 암호 만들기
/*
 * 간단하게 설명하자면, 조합을 이용하였는데 우선 알파벳을 오름차순 정렬한 후
 * 순서대로 뽑다가 중간에 끝까지 다 뽑아도 암호 길이를 충족 못시키면 종료하고,
 * 아니면 모음을 하나도 안 선택한 경우인데 모음을 이미 전부 지나쳐 버린 경우에 종료하고,
 * 아니면 암호 길이를 완성했을 때 유효성을 검사한 후 출력하고 종료한다.
 * 시간복잡도 : 15C8일 때가 가장 많은 경우의 수이므로 고려하지 않았다.
 */
public class Baekjoon_1759 {

	static int L,C;
	static char[] arr;
	static int lastVowel; // 마지막 모음의 인덱스
	static String str;
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		L = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		arr = new char[C];
		String line2 = br.readLine();
		for(int i=0; i<C; ++i) {
			arr[i] = line2.charAt(2*i);
		}
		Arrays.sort(arr); // 알파벳 오름차순 정렬
		
		StringBuilder sb = new StringBuilder();
		for(char ch : arr) {
			sb.append(ch);
		}
		str = sb.toString(); // 알파벳 전체 문자열을 만들기
		if(vowelsNum(str)==0) return; // 모음이 없으면 종료
		if(C<7) { // 알파벳이 7개보다 작으면
			if(vowelsNum(str)>C-2) return; // 자음이 2개이상 없으면 종료
		}
		func(0,0,"");
		System.out.print(result.toString());
	}
	
	public static void func(int n, int start, String pw) { // 조합
		if(start+L-n>C) { // 남은 문자수보다 앞으로 뽑아야 할 문자수가 더 많으면 종료
			return;
		}
		if(lastIdx(str)<start && vowelsNum(pw)==0) { // 검색할 문자가 마지막 모음 인덱스를 지나쳤고, 현재 문자열이 모음이 없으면
			return; // 나머지는 살펴볼 필요 없으므로 종료
		}
		if(n==L) { // 갯수를 다 채웠으면
			int num = vowelsNum(pw); // 모음갯수
			if(num>0 && num<L-1) { // 모음 1개이상, 자음 2개이상을 가지고 있으면
				result.append(pw).append("\n");
			}
			return;
		}
		for(int i=start; i<C; ++i) {
			func(n+1,i+1,pw+str.charAt(i));
		}
	}
	public static int vowelsNum(String str) { // 문자열의 모음 갯수 한봔
		return vowelsInfo(str)[0];
	}
	public static int lastIdx(String str) { // 문자열의 마지막 모음의 인덱스 반환
		return vowelsInfo(str)[1];
	}
	public static int[] vowelsInfo(String str) { // 존재하는 모음 갯수와 마지막 모음의 인덱스 리턴
		int num = 0;
		int lastIdx = 0;
		for(int i=0,size=str.length(); i<size; ++i) {
			if(str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='i'||str.charAt(i)=='o'||str.charAt(i)=='u') {
				num++;
				lastIdx=i;
			}
		}
		return new int[]{num, lastIdx};
	}
}
