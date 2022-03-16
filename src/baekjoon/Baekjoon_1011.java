package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Fly me to the Alpha Centauri
/*
 * 기본적으로 좌우대칭(?) 비슷한 모양으로 생각한다.
 * 양쪽 끝은 거리 1로 고정이고, 나머지는 순차적으로 2,3,4,... 순서대로
 * 양쪽에서 채워나가며 최대로 이동할 수 있는만큼 이동하고, 남은 거리는 1번 또는 2번의 이동으로 분배하여 계산한다.
 */
public class Baekjoon_1011 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; ++tc) {
			String[] str = br.readLine().split(" ");
			int distance = Integer.parseInt(str[1])-Integer.parseInt(str[0]); // 총 이동거리
			if(distance<=2) {
				sb.append(distance+"\n"); // 거리가 2이하면 1씩 움직임
				continue;
			}
			distance -= 2; // 제일 앞 뒤 거리 1씩은 무조건 고정이므로
			int answer = 2; // 제일 앞 뒤 거리 1씩은 최소 2회 이동
			if(distance<=2) { // 남은 거리가 1 또는 2 이면 한번에 갈 수 있으므로
				sb.append(++answer+"\n");
				continue;
			}
			int num = 2; // 앞 뒤쪽에서 각각 대칭으로 움직일 거리
			while(distance>num*2) { // 앞뒤 대칭으로 움직일 수 있는동안
				distance -= num*2;
				num++;
			}
			answer += (num-2)*2;
			if(distance<=num) answer++; // 남은 거리가 num이하이면 num만큼 한번 움직임
			else answer += 2; // 남은 거리가 num보다 크고 num*2보다 작으면 2번에 나누어서 움직일 수 있음
			sb.append(answer+"\n");
		}
		System.out.println(sb.toString());
	}
}