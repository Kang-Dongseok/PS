package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 리모컨
public class Baekjoon_1107 {
	
	static boolean[] broken;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String numStr = br.readLine();
		int num = Integer.parseInt(numStr);
		int min = Math.abs(num-100); // 최솟값
		int M = Integer.parseInt(br.readLine());
		if(M==0) { // 모든 숫자 사용 가능
			System.out.println(Math.min(numStr.length(), min)); // +-로 조절하거나 숫자를 바로 누르는 것 중 작은 값
			return;
		}else if(M==10) { // 모든 숫자 사용 불가능
			System.out.println(min); // +-로 조절한 값
			return;
		}
		broken = new boolean[10]; // 0~9 중에 사용 못하는 번호는 true
		String[] line = br.readLine().split(" ");
		for(int i=0; i<M; ++i) {
			broken[Integer.parseInt(line[i])]=true;
		}
		// num에서 가장 가까운 큰값 찾아서 비교하기
		int times = min; // +-로 조절가능한 횟수 이내에서 가까운 숫자 찾기
		int tmp = num;
		while(--times>0) {
			if(usable(tmp)) { // tmp가 가능한 숫자이면
				break;
			}else { // tmp가 불가능하면
				tmp++; // 1증가 시켜서 다시 시도
			}
		}
		min = Math.min(min, tmp-num+String.valueOf(tmp).length()); // tmp 숫자 갯수+tmp까지의 차이를 min값과비교하여 최솟값 갱신  
		// num에서 가장 가까운 작은값 찾아서 비교하기
		times = min; // 다시 초기화
		tmp = num; // 다시 초기화
		while(--times>0) {
			if(usable(tmp)) { // tmp가 가능한 숫자이면
				break;
			}else { // tmp가 불가능하면
				tmp--; // 1감소 시켜서 다시 시도
				if(tmp<0) break; // 0보다 작아지면 불가능하므로 종료
			}
		}
		if(tmp>=0) { // 0이상이면 최솟값 갱신
			min = Math.min(min, num-tmp+String.valueOf(tmp).length()); // tmp 숫자 갯수+tmp까지의 차이를 min값과비교하여 최솟값 갱신  
		}
		
		System.out.println(min);
	}
	
	public static boolean usable(int num) {
		boolean flag = true;
		while(true) {
			if(broken[num%10]) { // 일의자리 숫자가 사용 불가이면
				flag = false;
				break;
			}else {
				num /= 10; // 한자리 감소
			}
			if(num==0) break; // 숫자를 다 확인했으면 종료
		}
		return flag;
	}
}