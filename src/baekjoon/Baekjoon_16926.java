package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 배열 돌리기1
public class Baekjoon_16926 {

	static int N,M,R;
	static String[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]);
		arr = new String[N][M];
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine().split(" ");
		}
		int cnt = Math.min(N, M) / 2;
		for(int i=0; i<R; i++) { // R번 만큼 회전
			for(int j=0; j<cnt; j++) { // 큰 한번의 회전당 0~cnt번쨰 라인 전부 회전
				lineRotation(j);
			}
		}
		// 출력
		print();
		
	}

	public static void lineRotation(int line) { // 바깥부터 line번째 줄 회전, 제일 바깥 0번째 줄
		int rowIdx = M - 1 - line; // 해당 line의 가로 마지막 인덱스
		int colIdx = N - 1 - line; // 해당 line의 세로 마지막 인덱스
		String temp = arr[line][line]; // 왼쪽 젤 위 원소 임시 저장
		
		for(int i=line+1;i<=rowIdx;i++) { // 세로줄 좌로 한칸 이동
			arr[line][i-1] = arr[line][i];
		}
		for(int i=line+1;i<=colIdx;i++) { // 오른쪽줄 위로 한칸 이동
			arr[i-1][rowIdx] = arr[i][rowIdx];
		}
		for(int i=rowIdx-1;i>=line;i--) { // 아래줄 우로 한칸 이동
			arr[colIdx][i+1] = arr[colIdx][i];
		}
		for(int i=colIdx-1;i>=line+1;i--) { // 왼쪽줄 아래로 한칸 이동, 왼쪽 젤 위 시작점은 어차피 temp로 덮어씌울거기 떄문에 움직일 필요 없으므로 line+1까지만 이동
			arr[i+1][line] = arr[i][line];
		}
		
		arr[line+1][line] = temp; // 덮어씌우기
	}
	public static void print() {
		for(String[] st : arr) {
			for(String s : st) {
				sb.append(s).append(" ");
			}
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.print(sb);
	}
}
