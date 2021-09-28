package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 숫자놀이
public class Baekjoon_1755 {

	// one,two,three,four,five,six,seven,eight,nine,zero
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int M = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		String[] strArr = new String[N-M+1];
		for(int i=M; i<=N; i++) {
			strArr[i-M] = readNum(i); // 숫자들을 읽어서 문자열로 바꾼 후 저장
		}
		Arrays.sort(strArr); // 문자열을 사전순으로 정렬
		
		int[] numArr = new int[N-M+1];
		for(int i=M; i<=N; i++) {
			numArr[i-M] = strToNum(strArr[i-M]); // 문자열들을 읽어서 숫자들로 바꾼 후 저장
		}
		int length = numArr.length;
		for(int i=0; i<length; i++) {
			if(i%10==0 && i!=0) System.out.println();
			System.out.print(numArr[i]+" ");
		}
	}
	public static int strToNum(String str) { // 문자열 전체를 하나의 숫자로 바꿔줌
		String[] strArr = str.split(" ");
		if(strArr.length==1) {
			return strToDigit(strArr[0]);
		}else {
			return strToDigit(strArr[0])*10 + strToDigit(strArr[1]);
		}
	}
	public static int strToDigit(String str) { // 단어 하나를 한자리 숫자로 하나로 바꿈
		int num = 0;
		switch (str) {
		case "zero":
			num = 0;
			break;
		case "one":
			num = 1;
			break;
		case "two":
			num = 2;
			break;
		case "three":
			num = 3;
			break;
		case "four":
			num = 4;
			break;
		case "five":
			num = 5;
			break;
		case "six":
			num = 6;
			break;
		case "seven":
			num = 7;
			break;
		case "eight":
			num = 8;
			break;
		case "nine":
			num = 9;
			break;
		}
		return num;
	}
	public static String readNum(int num) { // 문자열 전체를 하나의 숫자로 바꿈
		String str = "";
		if(num<10) {
			str = readDigit(num);
		}else {
			str = readDigit(num/10)+" "+readDigit(num%10);
		}
		return str;
	}
	public static String readDigit(int n) { // 문자열을 한자리 숫자로 바꿈
		String str="";
		switch (n) {
		case 0:
			str="zero";
			break;
		case 1:
			str="one";
			break;
		case 2:
			str="two";
			break;
		case 3:
			str="three";
			break;
		case 4:
			str="four";
			break;
		case 5:
			str="five";
			break;
		case 6:
			str="six";
			break;
		case 7:
			str="seven";
			break;
		case 8:
			str="eight";
			break;
		case 9:
			str="nine";
			break;
		}
		return str;
	}
}
