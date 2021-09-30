package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 빗물
/*
 * 1층부터 한칸씩 물이 고일 수 있는지 확인해가며 brute force 방식으로 하였다.
 * 한층마다 왼쪽부터 오른쪽으로 쭉 살펴보면서 첫번쨰 기둥을 만나고나서 부터
 * 다음기둥을 만날때까지 계속 카운트하여 다음 기둥을 만나면 카운트를 다 더해주고,
 * 다음 기둥이 나타나지 않으면 카운트는 무산되며 다음 층으로 넘어간다.
 * 시간복잡도: 500*500=25,000 이기때문에 시간초과 걱정할 필요 없다.
 */
public class Baekjoon_14719 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int H = Integer.parseInt(str[0]);
		int W = Integer.parseInt(str[1]);
		int result = 0;
		boolean cntFlag; // 물이 채워질 칸을 세어도 되는지 여부
		str = br.readLine().split(" ");
		int[] blocks = new int[W];
		for(int i=0; i<W; ++i) {
			blocks[i] = Integer.parseInt(str[i]); // 블럭의 높이를 배열에 저장
		}
		for(int h=1; h<=H; ++h) { // 높이 1부터 시작
			int cnt = 0; // 각 층마다 몇 칸의 물을 채울 수 있는지
			cntFlag = false;
			for(int i=0; i<W; ++i) {
				if(blocks[i]>=h) {
					if(cntFlag) result += cnt; // 제일 처음 벽을 만나기전까지는 사실상 false 이므로 셀 수 없다.
					cntFlag = true; // 처음 벽을 만난 이후로는 true
					cnt = 0; // cnt를 다시 0으로 초기화
				}else {
					cnt++;
				}
			}
		}
		System.out.println(result);
	}
}