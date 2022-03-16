package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 단어 뒤집기2
public class Baekjoon_17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 태그를 그대로 append, 최종 출력할 문자열
		StringBuilder sb2 = new StringBuilder(); // 나머지 문자들을 뒤집어서 append
		String str = br.readLine();
		int size = str.length();
		int idx = 0; // '>'의 인덱스
		for(int i=0; i<size; i++) {
			char ch = str.charAt(i); // 문자 하나 읽어서
			if(ch==' ') {
				sb.append(sb2.reverse()); // 단어 뒤집어서 추가
				sb2.setLength(0); // sb2 초기화
				sb.append(ch); // 공백이면 바로 삽입
			} else if(ch=='<') { // 태그 시작하면 그 전에 단어들 뒤집이서 삽입
				sb.append(sb2.reverse()); // 단어 뒤집어서 추가
				sb2.setLength(0); // sb2 초기화
				idx = str.indexOf(">", idx+1); // 다음 순번의 '>'를 찾아 인덱스 저장 후
				sb.append(str.substring(i, idx+1)); // 태그만큼 sb에 저장
				i=idx; // 태그 건너뛰기
			} else {
				sb2.append(ch);
			}
			
		}
		sb.append(sb2.reverse()); // 문자열 끝나면 남아있는 단어 뒤집어서 삽입
		System.out.println(sb);
	}

}
