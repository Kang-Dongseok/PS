package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 모노미노도미노 2
/*
 * 문제에서 시키는대로만 배열을 잘 이동시키면 된다.
 * 깔끔해도 이동해보려고 한줄을 통째로 묶는 과정에서 ArrayList<boolean[]>을 사용했다가 괜히 얕은복사 때문에 디버깅에 고생만했다.
 * 차라리 처음부터 boolean[][]로 선언할 걸 그랬다.
 * 다른분 풀이를 보니 비트연산자로 해결하신 분도 있었다ㄷㄷ
 */
public class Baekjoon_20061 {
	
	static int sum,leftCnt;
	static ArrayList<boolean[]>[] blue,green;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		blue = new ArrayList[6];
		green = new ArrayList[6];
		for(int i=0; i<6; ++i) {
			blue[i] = new ArrayList<boolean[]>();
			blue[i].add(new boolean[] {false,false,false,false});
			green[i] = new ArrayList<boolean[]>();
			green[i].add(new boolean[] {false,false,false,false});
		}
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			String[] str = br.readLine().split(" ");
			int t = Integer.parseInt(str[0]);
			int x = Integer.parseInt(str[1]);
			int y = Integer.parseInt(str[2]);
			goBlue(t,x);
			goGreen(t,y);
//			System.out.println(i);
//			System.out.println("before");
//			print();
			removeLine(); // 파랑&초록 줄 제거,블럭이동,점수 증가
			removeSpecialLine();
//			System.out.println("after");
//			print();
		}
//		print();
		for(int i=2; i<6; ++i) { // 연한칸은 블럭 없으므로 나머지만 계산
			for(int j=0; j<4; ++j) {
				if(blue[i].get(0)[j]) leftCnt++;
				if(green[i].get(0)[j]) leftCnt++;
			}
		}
		System.out.println(sum);
		System.out.println(leftCnt);
	}
	
	public static void goBlue(int t, int r) {
		int col = 0;
		if(t==1) { // 1X1
			for(int c=1; c<6; ++c) {
				if(!blue[c].get(0)[r]) { // 빈칸이면
					col=c; // 열 증가
				}else {
					break;
				}
			}
			blue[col].get(0)[r]=true;
		}else if(t==2) { // 1X2
			for(int c=1; c<5; ++c) {
				if(!blue[c].get(0)[r] && !blue[c+1].get(0)[r]) { // 가로 2칸 빈칸이면
					col=c; // 열 증가
				}else {
					break;
				}
			}
			blue[col].get(0)[r]=true;
			blue[col+1].get(0)[r]=true;
		}else { // 2X1
			for(int c=1; c<6; ++c) {
				if(!blue[c].get(0)[r] && !blue[c].get(0)[r+1]) { // 세로 2칸 빈칸이면
					col=c; // 열 증가
				}else {
					break;
				}
			}
			blue[col].get(0)[r]=true;
			blue[col].get(0)[r+1]=true;
		}
	}
	
	public static void goGreen(int t, int c) {
		int row = 0;
		if(t==1) { // 1X1
			for(int r=1; r<6; ++r) {
				if(!green[r].get(0)[c]) { // 빈칸이면
					row=r; // 열 증가
				}else {
					break;
				}
			}
			green[row].get(0)[c]=true;
		}else if(t==2) { // 1X2
			for(int r=1; r<6; ++r) {
				if(!green[r].get(0)[c] && !green[r].get(0)[c+1]) { // 가로 2칸 빈칸이면
					row=r; // 열 증가
				}else {
					break;
				}
			}
			green[row].get(0)[c]=true;
			green[row].get(0)[c+1]=true;
		}else { // 2X1
			for(int r=1; r<5; ++r) {
				if(!green[r].get(0)[c] && !green[r+1].get(0)[c]) { // 세로 2칸 빈칸이면
					row=r; // 열 증가
				}else {
					break;
				}
			}
			green[row].get(0)[c]=true;
			green[row+1].get(0)[c]=true;
		}
	}
	
	public static void removeLine() {
		for(int i=2; i<6; ++i) { // 연한칸은 무시, 2부터 시작
			// 파랑줄 제거
			boolean[] cur = blue[i].get(0);
			if(cur[0] && cur[1] && cur[2] && cur[3]) { // 꽉찬 줄이 있으면
				for(int j=i; j>0; --j) {
					blue[j].clear();
					blue[j].add(blue[j-1].get(0));
//					blue[j]=blue[j-1]; // 그 줄을 지우고 한칸씩 이동
				}
				blue[0] = new ArrayList<boolean[]>();
				blue[0].add(new boolean[] {false,false,false,false}); // 제일 앞에 새로운줄 생성
				sum++; // 점수 증가
			}
			// 초록줄 제거
			cur = green[i].get(0);
			if(cur[0] && cur[1] && cur[2] && cur[3]) { // 꽉찬 줄이 있으면
				for(int j=i; j>0; --j) {
					green[j].clear();
					green[j].add(green[j-1].get(0));
				}
				green[0] = new ArrayList<boolean[]>();
				green[0].add(new boolean[] {false,false,false,false}); // 제일 앞에 새로운줄 생성
				sum++; // 점수 증가
			}
		}
	}
	
	public static void removeSpecialLine() { // 연한 줄 제거
		int blueCnt = 0;
		int greenCnt = 0;
		for(int i=0; i<2; ++i) {
			boolean[] cur = blue[i].get(0);
			if(cur[0] || cur[1] || cur[2] || cur[3]) { // 블럭이 하나라도 있으면
				blueCnt++; // 파랑줄 카운트
			}
			cur = green[i].get(0);
			if(cur[0] || cur[1] || cur[2] || cur[3]) { // 블럭이 하나라도 있으면
				greenCnt++; // 초록줄 카운트
			}
		}
		if(blueCnt>0) { // 옮길 줄이 있으면
			for(int i=5; i>=blueCnt; --i) {
				blue[i].clear();
				blue[i].add(blue[i-blueCnt].get(0));
			}
			for(int i=blueCnt-1; i>=0; --i) {
				blue[i].clear(); // 줄 정보 삭제
				blue[i].add(new boolean[] {false,false,false,false}); // 쥴 초기화
			}
		}
		if(greenCnt>0) { // 옮길 줄이 있으면
			for(int i=5; i>=greenCnt; --i) {
				green[i].clear();
				green[i].add(green[i-greenCnt].get(0));
			}
			for(int i=greenCnt-1; i>=0; --i) {
				green[i].clear(); // 줄 정보 삭제
				green[i].add(new boolean[] {false,false,false,false}); // 쥴 초기화
			}
		}
	}
//	public static void print() {
//		for(int i=0; i<4; ++i) {
//			for(int j=0; j<6; ++j) {
//				if(blue[j].get(0)[i]) {
//					System.out.print(1+" ");
//				}else {
//					System.out.print(0+" ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for(int j=0; j<6; ++j) {
//			for(boolean a : green[j].get(0)) {
//				if(a) {
//					System.out.print(1+" ");
//				}else {
//					System.out.print(0+" ");
//				}
//			}
//			System.out.println();
//		}
//	}
}